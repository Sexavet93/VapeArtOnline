package com.vapeart.presentation.utils

import com.vapeart.domain.models.FavoriteItem
import com.vapeart.domain.models.SelectedItem

abstract class ItemsManagerImpl: ItemsManager {

    override fun addToCart(item: SelectedItem) {
    }

    override fun deleteFromCart(item: SelectedItem) {
    }

    override fun deleteItem(item: FavoriteItem) {
    }
}