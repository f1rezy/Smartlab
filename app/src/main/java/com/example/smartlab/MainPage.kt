package com.example.smartlab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.security.crypto.EncryptedSharedPreferences
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.smartlab.adapters.NewsAdapter
import com.example.smartlab.databinding.ActivityMainPageBinding
import com.example.smartlab.models.News
import org.json.JSONArray
import org.json.JSONObject

class MainPage : AppCompatActivity() {
    lateinit var binding: ActivityMainPageBinding
    private val adapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        val encryptedPreferences = EncryptedSharedPreferences.create(
            "user",
            "prefrences_master_key",
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

        getNews()

    }

    private fun getNews() {
        val url = "https://medic.madskill.ru/api/news"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                    response->
                val obj = JSONArray(response)
                for (i in 0 until obj.length()){
                    val elem = obj.getJSONObject(i)
                    val id = elem.getInt("id")
                    val title = elem.getString("name")
                    val description = elem.getString("description")
                    val price = elem.getString("price")
                    val imageUrl = elem.getString("image")
                    addNews(id, title, description, price, imageUrl)
                }
            }, {})
        queue.add(stringRequest)
    }

    private fun init() {
        binding.apply {
            newsRecycler.layoutManager = LinearLayoutManager(this@MainPage, RecyclerView.HORIZONTAL, false)
            newsRecycler.adapter = adapter
        }
    }

    private fun addNews(id: Int, title: String, description: String, price: String, imageUrl: String) {
        binding.apply {
            val news = News(id, title, description, "$price ₽", imageUrl)
            adapter.addNews(news)
        }
    }

}