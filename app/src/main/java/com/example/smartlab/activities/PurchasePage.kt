package com.example.smartlab.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.smartlab.Order
import com.example.smartlab.databinding.ActivityPurchasePageBinding
import kotlinx.coroutines.delay

class PurchasePage : AppCompatActivity() {
    lateinit var binding: ActivityPurchasePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchasePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goToBuyButton.setOnClickListener {
            intent = Intent(this, CheckPage::class.java)
            startActivity(intent)
        }
    }
}