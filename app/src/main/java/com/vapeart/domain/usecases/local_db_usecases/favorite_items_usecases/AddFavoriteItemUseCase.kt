package com.vapeart.domain.usecases.local_db_usecases.favorite_items_usecases

import com.vapeart.domain.models.FavoriteItem
import com.vapeart.domain.repositories.RoomRepository

class AddFavoriteItemUseCase(private val roomRepo: RoomRepository) {

    operator fun invoke(item: FavoriteItem){
        roomRepo.addFavoriteItem(item)
    }
}