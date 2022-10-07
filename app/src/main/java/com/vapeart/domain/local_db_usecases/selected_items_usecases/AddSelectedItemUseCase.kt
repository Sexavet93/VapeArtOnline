package com.vapeart.domain.local_db_usecases.selected_items_usecases

import com.vapeart.data.room.SelectedItem
import com.vapeart.domain.RoomRepository

class AddSelectedItemUseCase(private val roomRepo: RoomRepository) {

    fun addSelectedItem(item: SelectedItem){
        roomRepo.addSelectedItem(item)
    }
}