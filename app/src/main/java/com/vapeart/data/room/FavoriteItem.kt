package com.vapeart.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteItem(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    var isFavorite: Boolean = false
)