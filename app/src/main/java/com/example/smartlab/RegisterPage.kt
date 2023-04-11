package com.example.smartlab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.smartlab.databinding.ActivityRegisterPageBinding
import org.json.JSONObject

class RegisterPage : AppCompatActivity() {
    lateinit var binding: ActivityRegisterPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.enterEmailButton.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            postRequest(email)
        }
    }

    private fun postRequest(email: String) {
        val url = "https://medic.madskill.ru/api/sendCode"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = object : StringRequest(Request.Method.POST, url,
            {
                response->
                val obj = JSONObject(response)
                val message = obj.getString("message")
                if (message == "Успешно код отправлен") {
                    val intent = Intent(this, CodePage::class.java)
                    intent.putExtra("email", email)
                    startActivity(intent)
                }
            }, {}) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["email"] = email
                    return headers
                }
            }
        queue.add(stringRequest)
    }
}