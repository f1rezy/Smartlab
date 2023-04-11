package com.example.smartlab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.security.crypto.EncryptedSharedPreferences

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val encryptedPreferences = EncryptedSharedPreferences.create(
            "user",
            "prefrences_master_key",
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        if (encryptedPreferences.contains("token")) {
            intent = Intent(this, MainPage::class.java)
        }
        else {
            intent = Intent(this, RegisterPage::class.java)
        }
        startActivity(intent)
        finish()
    }
}