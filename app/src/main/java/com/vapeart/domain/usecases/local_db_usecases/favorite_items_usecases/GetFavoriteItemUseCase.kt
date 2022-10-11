package com.vapeart.domain.usecases.local_db_usecases.favorite_items_usecases

import androidx.lifecycle.LiveData
import com.vapeart.data.room.FavoriteItem
import com.vapeart.domain.repositories.RoomRepository

class GetFavoriteItemUseCase(private val roomRepo: RoomRepository) {

    operator fun invoke(id: String): LiveData<FavoriteItem>{
        return roomRepo.getFavoriteItem(id)
    }
}