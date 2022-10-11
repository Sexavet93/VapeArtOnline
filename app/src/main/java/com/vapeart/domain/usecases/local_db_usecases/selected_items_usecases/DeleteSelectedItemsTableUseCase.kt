package com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases

import com.vapeart.domain.repositories.RoomRepository

class DeleteSelectedItemsTableUseCase(private val roomRepo: RoomRepository) {

    operator fun invoke(){
        roomRepo.deleteTable()
    }
}