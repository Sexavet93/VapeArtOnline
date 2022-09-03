package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vapeart.data.repositories.RoomRepository
import com.vapeart.data.room.SelectedItem

class DetailsFragmentViewModel: ViewModel() {

    private val roomRepo = RoomRepository.getInstance()

    private val _selectedItemLiveData: MutableLiveData<SelectedItem> = MutableLiveData()
    val selectedItemLiveData: LiveData<SelectedItem>
        get() = _selectedItemLiveData

    fun addItem(item: SelectedItem){
        roomRepo.addItem(item)
    }

//    fun deleteItem(item: SelectedItem): Boolean{
//        roomRepo.deleteItem(item)
//    }
}