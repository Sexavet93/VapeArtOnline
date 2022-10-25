package com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases

import com.vapeart.domain.models.SelectedItem
import com.vapeart.domain.repositories.RoomRepository

class AddSelectedItemUseCase(private val roomRepo: RoomRepository) {

    operator fun invoke(item: SelectedItem){
        roomRepo.addSelectedItem(item)
    }
}