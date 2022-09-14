package com.vapeart.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.SelectedItem
import com.vapeart.domain.Item

object DiffCallbacks {

    val mainAdapterDiffCallback = object : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    val wishListDiffCallback = object : DiffUtil.ItemCallback<FavoriteItem>() {
        override fun areItemsTheSame(oldItem: FavoriteItem, newItem: FavoriteItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoriteItem, newItem: FavoriteItem): Boolean {
            return oldItem == newItem
        }

    }

    val cartListDiffCallback = object : DiffUtil.ItemCallback<SelectedItem>(){
        override fun areItemsTheSame(oldItem: SelectedItem, newItem: SelectedItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SelectedItem, newItem: SelectedItem): Boolean {
            return oldItem == newItem
        }
    }

}