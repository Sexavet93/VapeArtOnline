package com.vapeart.domain.usecases.local_db_usecases.favorite_items_usecases

import com.vapeart.data.room.FavoriteItem
import com.vapeart.domain.repositories.RoomRepository

class DeleteFavoriteItemUseCase(private val roomRepo: RoomRepository) {

    operator fun invoke(item: FavoriteItem){
        roomRepo.deleteFavoriteItem(item)
    }
}