package com.example.mynamesactions.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.mynamesactions.data.ListItem

class ListComparator : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.listId == newItem.listId
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }
}