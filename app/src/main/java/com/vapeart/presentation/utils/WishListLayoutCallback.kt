package com.vapeart.presentation.utils

import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.SelectedItem

interface WishListLayoutCallback {

    fun addToCartCallback(item: SelectedItem)

    fun deleteItemCallback(item: FavoriteItem)
}