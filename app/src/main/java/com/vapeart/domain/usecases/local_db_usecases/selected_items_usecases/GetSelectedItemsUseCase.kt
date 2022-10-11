package com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases

import androidx.lifecycle.LiveData
import com.vapeart.data.room.SelectedItem
import com.vapeart.domain.repositories.RoomRepository

class GetSelectedItemsUseCase(private val roomRepo: RoomRepository) {

    operator fun invoke(): LiveData<List<SelectedItem>>{
        return roomRepo.getSelectedItems()
    }
}