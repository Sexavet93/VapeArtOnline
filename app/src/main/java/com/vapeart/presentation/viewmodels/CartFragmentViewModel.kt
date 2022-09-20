package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vapeart.data.repositories.RoomRepository
import com.vapeart.data.room.SelectedItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartFragmentViewModel @Inject constructor(private var roomRepository: RoomRepository): ViewModel() {

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