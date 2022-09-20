package com.vapeart.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    var id: String = "",
    var category: String = "",
    var subCategory: String = "",
    var manufacturer: String = "",
    var name: String = "",
    var oldPrice: String = "0",
    var currentPrice: String = "0",
    var description: String = "",
    var saleQuantityPerMonth: Int = 0,
    var itemNew: Boolean = false,
    var imageUri: String = ""
): Parcelable