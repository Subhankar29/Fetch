package com.example.mynamesactions.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mynamesactions.data.ListItem
import com.example.mynamesactions.databinding.ItemListBinding

class ListIDAdapter(
    private val onItemClick: (Int) -> Unit
) : ListAdapter<ListItem, ListIDViewHolder>(ListComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListIDViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListIDViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ListIDViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }
}