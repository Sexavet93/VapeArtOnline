package com.vapeart.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.vapeart.domain.Item

class DiffCallback: DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}