package com.j.wear001.presentation

import android.content.Context
import android.media.RouteListingPreference
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.j.wear001.R
import com.j.wear001.databinding.ActivityMainBinding
import com.j.wear001.databinding.RecyclerItemBinding
import com.j.wear001.presentation.user.Item

class MyAdapter(private val items: MutableList<Item>, private val itemClickListener: (Item) -> Unit) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) = with(binding) {
            textViewName.text = item.title
            textViewDescription.text = item.description
            //checkBoxSelected.isChecked = item.isSelected
            root.setOnClickListener { itemClickListener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData(newItems: List<Item>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

