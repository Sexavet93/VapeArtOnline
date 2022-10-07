package com.vapeart.domain.local_db_usecases.selected_items_usecases

import androidx.lifecycle.LiveData
import com.vapeart.data.room.SelectedItem
import com.vapeart.domain.RoomRepository

class GetSelectedItemsUseCase(private val roomRepo: RoomRepository) {

    fun getSelectedItems(): LiveData<List<SelectedItem>>{
        return roomRepo.getSelectedItems()
    }
}