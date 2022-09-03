package com.vapeart.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SelectedItem(
    @PrimaryKey
    val id: String,
    var amount: Int
)