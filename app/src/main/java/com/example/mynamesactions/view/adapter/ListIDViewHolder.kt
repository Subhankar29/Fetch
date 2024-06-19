package com.example.mynamesactions.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.mynamesactions.data.ListItem
import com.example.mynamesactions.databinding.ItemListBinding


class ListIDViewHolder (
    private val binding: ItemListBinding,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemClick(position)
            }
        }
    }

    fun bind(listId: ListItem) {
        binding.apply {
            idText.text = listId.listId.toString()
        }
    }
}
