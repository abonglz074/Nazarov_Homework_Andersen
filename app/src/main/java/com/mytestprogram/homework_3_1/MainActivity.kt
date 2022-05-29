package com.mytestprogram.homework_3_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mytestprogram.homework_3_1.databinding.ActivityMainBinding
import com.mytestprogram.homework_3_1.databinding.ActivityTask2Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.secondScreenBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, Task2::class.java)
            startActivity(intent)
        }
    }
}