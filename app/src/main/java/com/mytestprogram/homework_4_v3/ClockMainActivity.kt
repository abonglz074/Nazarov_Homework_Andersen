package com.mytestprogram.homework_4_v3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mytestprogram.homework_4_v3.databinding.ClockViewBinding
import java.util.*

class ClockMainActivity : AppCompatActivity() {

    private lateinit var binding: ClockViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ClockViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR + 1)
        val min = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)
        binding.clockView.setCurrentTime(hour,min,second)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.clockView.stop()
    }
}