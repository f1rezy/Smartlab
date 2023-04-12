package com.example.smartlab.adapters

import com.example.smartlab.models.Service
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartlab.R
import com.example.smartlab.databinding.ServiceItemBinding
import com.example.smartlab.models.News

class ServiceAdapter: RecyclerView.Adapter<ServiceAdapter.ServiceHolder>() {
    val servicesList = ArrayList<Service>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.service_item, parent, false)
        return ServiceHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceHolder, position: Int) {
        holder.bind(servicesList[position])
    }

    override fun getItemCount(): Int {
        return servicesList.size
    }

    class ServiceHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ServiceItemBinding.bind(view)

        fun bind(service: Service) = with(binding){
            title.text = service.name
            timeResult.text = service.time_result
            price.text = service.price
        }
    }

    fun addService(service: Service) {
        servicesList.add(service)
        notifyDataSetChanged()
    }
}