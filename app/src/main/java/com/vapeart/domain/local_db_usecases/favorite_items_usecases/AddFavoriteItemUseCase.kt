package com.vapeart.domain.local_db_usecases.favorite_items_usecases

import androidx.lifecycle.LiveData
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.SelectedItem
import com.vapeart.domain.RoomRepository

class AddFavoriteItemUseCase(private val roomRepo: RoomRepository) {

    fun addFavoriteItem(item: FavoriteItem){
        roomRepo.addFavoriteItem(item)
    }
}