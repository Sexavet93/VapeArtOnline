package com.vapeart.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SelectedItem(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    var amount: Int = 0,
    var isInFavorites: Boolean = false
)