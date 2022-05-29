package com.mytestprogram.homework_3_1

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mytestprogram.homework_3_1.databinding.ActivityTask2Binding

class Task2 : AppCompatActivity() {

    private lateinit var binding: ActivityTask2Binding

    private lateinit var imageUrl: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener {

            imageUrl = binding.editText.text.toString()

            Glide.with(this)
                .load(imageUrl)
                .error(R.drawable.ic_baseline_image_24)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Toast.makeText(
                            applicationContext,
                            "Please enter correct URL",
                            Toast.LENGTH_SHORT
                        ).show()
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(binding.imageView)
        }

        binding.goBack.setOnClickListener {
            val intent = Intent(this@Task2, MainActivity::class.java)
            startActivity(intent)
        }

    }

}