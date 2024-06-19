package com.example.mynamesactions.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.mynamesactions.data.Names
import com.example.mynamesactions.databinding.ItemNewsBinding

class NamesViewHolder(
    private val binding: ItemNewsBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: Names) {
        binding.apply {
            idText.text = news.id.toString()
            nameText.text = news.name
        }
    }
}