package com.vapeart.data.mappers

import com.vapeart.data.room.FavoriteItemEntity
import com.vapeart.domain.models.FavoriteItem

class FavoriteItemMapper {

    companion object{
        fun favoriteItemToEntity(item: FavoriteItem): FavoriteItemEntity{
            return FavoriteItemEntity(
                id = item.id,
                itemName = item.itemName,
                imageUri = item.imageUri,
                currentPrice =  item.currentPrice,
                manufacturer = item.manufacturer,
                isFavorite = item.isFavorite
            )
        }

        fun entityItemToFavoriteItem(item: FavoriteItemEntity): FavoriteItem{
            return FavoriteItem(
                id = item.id,
                itemName = item.itemName,
                imageUri = item.imageUri,
                currentPrice =  item.currentPrice,
                manufacturer = item.manufacturer,
                isFavorite = item.isFavorite
            )
        }
    }
}