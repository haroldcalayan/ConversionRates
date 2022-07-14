package com.haroldcalayan.conversionrates.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.conversionrates.BR
import com.haroldcalayan.conversionrates.R
import com.haroldcalayan.conversionrates.databinding.AdapterConversionBinding

class ConversionAdapter(private var data: List<String>, private var listener: ConversionAdapterListener) : RecyclerView.Adapter<ConversionAdapter.ConversionAdapterViewHolder>() {

    interface ConversionAdapterListener {
        fun onItemClick(item: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversionAdapterViewHolder {
        val binding: AdapterConversionBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_conversion,
            parent,
            false
        )
        return ConversionAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConversionAdapterViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener { listener.onItemClick(data[position]) }
    }

    override fun getItemCount() = data.size

    fun updateData(data: List<String>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ConversionAdapterViewHolder(private val binding: AdapterConversionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}