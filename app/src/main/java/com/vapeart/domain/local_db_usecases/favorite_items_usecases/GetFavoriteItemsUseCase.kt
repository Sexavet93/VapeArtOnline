package com.vapeart.domain.local_db_usecases.favorite_items_usecases

import androidx.lifecycle.LiveData
import com.vapeart.data.room.FavoriteItem
import com.vapeart.domain.RoomRepository

class GetFavoriteItemsUseCase(private val roomRepo: RoomRepository) {

    fun getFavoriteItems(): LiveData<List<FavoriteItem>>{
        return roomRepo.getFavoriteItems()
    }
}