package com.vapeart.presentation.utils

import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.SelectedItem

interface ItemsManager {

    fun addToCart(item: SelectedItem)

    fun deleteFromCart(item: SelectedItem)

    fun deleteItem(item: FavoriteItem)
}