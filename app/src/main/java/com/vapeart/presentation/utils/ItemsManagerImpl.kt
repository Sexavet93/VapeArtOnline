package com.vapeart.presentation.utils

import androidx.fragment.app.Fragment
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.SelectedItem

abstract class ItemsManagerImpl: Fragment(), ItemsManager {

    override fun addToCart(item: SelectedItem) {
    }

    override fun deleteFromCart(item: SelectedItem) {
    }

    override fun deleteItem(item: FavoriteItem) {
    }
}