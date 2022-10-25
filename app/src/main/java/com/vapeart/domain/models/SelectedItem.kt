package com.vapeart.domain.models

data class SelectedItem(
    val id: String,
    var itemName: String = "",
    var imageUri: String = "",
    var currentPrice: String = "",
    var manufacturer: String = "",
    var amount: Int = 0
)