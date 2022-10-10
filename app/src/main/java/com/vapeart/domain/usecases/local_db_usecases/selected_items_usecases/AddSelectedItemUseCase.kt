package com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases

import com.vapeart.data.room.SelectedItem
import com.vapeart.domain.repositories.RoomRepository

class AddSelectedItemUseCase(private val roomRepo: RoomRepository) {

    fun addSelectedItem(item: SelectedItem){
        roomRepo.addSelectedItem(item)
    }
}