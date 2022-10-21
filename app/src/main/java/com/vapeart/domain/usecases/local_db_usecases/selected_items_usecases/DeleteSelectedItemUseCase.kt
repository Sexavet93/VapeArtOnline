package com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases

import com.vapeart.data.room.SelectedItem
import com.vapeart.domain.repositories.RoomRepository

class DeleteSelectedItemUseCase(private val roomRepo: RoomRepository) {

    operator fun invoke(item: SelectedItem){
        roomRepo.deleteSelectedItem(item)
    }
}