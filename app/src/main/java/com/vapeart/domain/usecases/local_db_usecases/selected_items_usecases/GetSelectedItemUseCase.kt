package com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases

import androidx.lifecycle.LiveData
import com.vapeart.data.room.SelectedItem
import com.vapeart.domain.repositories.RoomRepository

class GetSelectedItemUseCase(private val roomRepo: RoomRepository) {

    fun getSelectedItem(id: String): LiveData<SelectedItem>{
        return roomRepo.getSelectedItem(id)
    }
}