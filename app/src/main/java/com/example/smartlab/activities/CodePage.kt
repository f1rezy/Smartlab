package com.example.smartlab.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.security.crypto.EncryptedSharedPreferences
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.smartlab.databinding.ActivityCodePageBinding
import org.json.JSONObject

class CodePage : AppCompatActivity() {
    lateinit var binding: ActivityCodePageBinding
    private var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        email = intent.getStringExtra("email").toString()
        binding.otpView.setOtpCompletionListener {
            getSignInRequest(binding.otpView.text.toString())
        }
    }

    fun getSignInRequest(code: String) {
        val url = "https://medic.madskill.ru/api/signin"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = object : StringRequest(Request.Method.POST, url,
            { response->
                val obj = JSONObject(response)
                val token = obj.getString("token")
                val encryptedPreferences = EncryptedSharedPreferences.create(
                    "user",
                    "prefrences_master_key",
                    this,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
                encryptedPreferences.edit().putString("email", email).apply()
                encryptedPreferences.edit().putString("token", token).apply()
                val intent = Intent(this, MainPage::class.java)
                startActivity(intent)
            }, {}) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["email"] = email
                headers["code"] = code
                return headers
            }
        }
        queue.add(stringRequest)
    }
}