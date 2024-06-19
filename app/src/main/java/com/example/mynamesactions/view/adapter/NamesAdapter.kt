package com.example.mynamesactions.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mynamesactions.data.Names
import com.example.mynamesactions.databinding.ItemNewsBinding

class NamesAdapter() : ListAdapter<Names, NamesViewHolder>(NamesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NamesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }
}
