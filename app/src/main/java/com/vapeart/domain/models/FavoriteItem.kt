package com.vapeart.domain.models

data class FavoriteItem(
    val id: String,
    var itemName: String = "",
    var imageUri: String = "",
    var currentPrice: String = "",
    var manufacturer: String = "",
    var isFavorite: Boolean = false
)