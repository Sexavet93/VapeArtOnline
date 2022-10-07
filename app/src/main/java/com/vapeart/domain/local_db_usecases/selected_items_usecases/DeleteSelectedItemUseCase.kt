package com.vapeart.domain.local_db_usecases.selected_items_usecases

import com.vapeart.data.room.SelectedItem
import com.vapeart.domain.RoomRepository

class DeleteSelectedItemUseCase(private val roomRepo: RoomRepository) {

    fun deleteSelectedItem(item: SelectedItem){
        roomRepo.deleteSelectedItem(item)
    }
}