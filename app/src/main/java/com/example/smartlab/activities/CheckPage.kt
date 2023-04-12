package com.example.smartlab.activities

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartlab.databinding.ActivityCheckPageBinding

class CheckPage : AppCompatActivity() {
    lateinit var binding: ActivityCheckPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}