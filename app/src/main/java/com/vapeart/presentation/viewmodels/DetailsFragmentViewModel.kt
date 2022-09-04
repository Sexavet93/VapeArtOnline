package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vapeart.data.repositories.RoomRepository
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.SelectedItem

class DetailsFragmentViewModel: ViewModel() {

    private val roomRepo = RoomRepository.getInstance()

    fun addSelectedItem(item: SelectedItem){
        roomRepo.addSelectedItem(item)
    }

    fun addFavoriteItem(item: FavoriteItem){
        roomRepo.addFavoriteItem(item)
    }

    fun deleteFavoriteItem(item: FavoriteItem){
        roomRepo.deleteFavoriteItem(item)
    }
}