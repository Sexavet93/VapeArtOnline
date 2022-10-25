package com.vapeart.data.mappers

import com.vapeart.data.room.SelectedItemEntity
import com.vapeart.domain.models.SelectedItem

class SelectedItemMapper {

    companion object{
        fun selectedItemToEntity(item: SelectedItem): SelectedItemEntity{
            return SelectedItemEntity(
                id = item.id,
                itemName = item.itemName,
                imageUri= item.imageUri,
                currentPrice = item.currentPrice,
                manufacturer = item.manufacturer,
                amount = item.amount
            )
        }

        fun entityItemToSelectedItem(item: SelectedItemEntity): SelectedItem{
            return SelectedItem(
                id = item.id,
                itemName = item.itemName,
                imageUri= item.imageUri,
                currentPrice = item.currentPrice,
                manufacturer = item.manufacturer,
                amount = item.amount
            )
        }
    }
}