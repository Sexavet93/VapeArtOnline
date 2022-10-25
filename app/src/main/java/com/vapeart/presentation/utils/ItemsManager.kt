package com.vapeart.presentation.utils

import com.vapeart.domain.models.FavoriteItem
import com.vapeart.domain.models.SelectedItem

interface ItemsManager {

    fun addToCart(item: SelectedItem)

    fun deleteFromCart(item: SelectedItem)

    fun deleteItem(item: FavoriteItem)
}