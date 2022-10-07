package com.vapeart.domain.local_db_usecases.selected_items_usecases

import com.vapeart.domain.RoomRepository

class DeleteSelectedItemsTableUseCase(private val roomRepo: RoomRepository) {

    fun deleteTable(){
        roomRepo.deleteTable()
    }
}