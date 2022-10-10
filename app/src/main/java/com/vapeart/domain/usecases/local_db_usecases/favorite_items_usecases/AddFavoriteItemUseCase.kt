package com.vapeart.domain.usecases.local_db_usecases.favorite_items_usecases

import com.vapeart.data.room.FavoriteItem
import com.vapeart.domain.repositories.RoomRepository

class AddFavoriteItemUseCase(private val roomRepo: RoomRepository) {

    fun addFavoriteItem(item: FavoriteItem){
        roomRepo.addFavoriteItem(item)
    }
}