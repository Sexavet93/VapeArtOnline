package com.vapeart.domain.usecases.local_db_usecases.favorite_items_usecases

import androidx.lifecycle.LiveData
import com.vapeart.data.room.FavoriteItem
import com.vapeart.domain.repositories.RoomRepository

class GetFavoriteItemsUseCase(private val roomRepo: RoomRepository) {

    operator fun invoke(): LiveData<List<FavoriteItem>>{
        return roomRepo.getFavoriteItems()
    }
}