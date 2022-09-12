package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vapeart.data.repositories.RoomRepository
import com.vapeart.data.room.SelectedItem

class CartFragmentViewModel: ViewModel() {

    private val roomRepository: RoomRepository = RoomRepository.getInstance()
    val selectedItemsLiveData: LiveData<List<SelectedItem>> = roomRepository.getSelectedItems()

    fun deleteSelectedItem(item: SelectedItem){
        roomRepository.deleteSelectedItem(item)
    }

    fun addSelectedItem(item: SelectedItem){
        roomRepository.addSelectedItem(item)
    }

    fun deleteAllItems(){
        roomRepository.deleteTable()
    }
}