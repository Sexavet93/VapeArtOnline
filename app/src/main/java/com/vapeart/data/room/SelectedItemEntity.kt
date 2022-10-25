package com.vapeart.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SelectedItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    var itemName: String = "",
    var imageUri: String = "",
    var currentPrice: String = "",
    var manufacturer: String = "",
    var amount: Int = 0
)