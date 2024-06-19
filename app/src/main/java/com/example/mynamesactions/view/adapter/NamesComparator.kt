package com.example.mynamesactions.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.mynamesactions.data.Names

class NamesComparator: DiffUtil.ItemCallback<Names>() {
    override fun areItemsTheSame(oldItem: Names, newItem: Names): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Names, newItem: Names): Boolean {
        return oldItem == newItem
    }
}