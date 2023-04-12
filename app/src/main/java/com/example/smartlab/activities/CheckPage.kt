package com.example.smartlab.activities

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.smartlab.databinding.ActivityCheckPageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class CheckPage : AppCompatActivity() {
    lateinit var binding: ActivityCheckPageBinding
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startCountDownTimer(3000)

        binding.onTheMainButton.setOnClickListener {
            val intent = Intent(this, MainPage::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun startCountDownTimer(timeMillis: Long){
        timer?.cancel()
        timer = object : CountDownTimer(timeMillis, 1){
            override fun onTick(timeM: Long) { }

            override fun onFinish() {
                binding.progressText.text = "Производим оплату..."
                secondCountDownTimer(3000)
            }
        }.start()
    }

    private fun secondCountDownTimer(timeMillis: Long){
        timer?.cancel()
        timer = object : CountDownTimer(timeMillis, 1){
            override fun onTick(timeM: Long) { }

            override fun onFinish() {
                binding.loadElem.visibility = View.GONE
                binding.result.visibility = View.VISIBLE
            }
        }.start()
    }
}