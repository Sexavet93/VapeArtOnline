package com.vapeart.domain.local_db_usecases.favorite_items_usecases

import com.vapeart.data.room.FavoriteItem
import com.vapeart.domain.RoomRepository

class DeleteFavoriteItemUseCase(private val roomRepo: RoomRepository) {

    fun deleteFavoriteItem(item: FavoriteItem){
        roomRepo.deleteFavoriteItem(item)
    }
}