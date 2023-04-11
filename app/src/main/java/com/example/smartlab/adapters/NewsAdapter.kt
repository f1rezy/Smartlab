package com.example.smartlab.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.SizeTransform
import androidx.recyclerview.widget.RecyclerView
import com.example.smartlab.R
import com.example.smartlab.databinding.NewsItemBinding
import com.example.smartlab.models.News
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    val newsList = ArrayList<News>()

    class NewsHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = NewsItemBinding.bind(view)

        fun bind(news: News) = with(binding){
            newsTitle.text = news.name
            newsDescription.text = news.description
            newsPrice.text = news.price
            Picasso.with(binding.root.context).load(news.imageUrl).into(newsImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsHolder(view)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun addNews(news: News) {
        newsList.add(news)
        notifyDataSetChanged()
    }
}