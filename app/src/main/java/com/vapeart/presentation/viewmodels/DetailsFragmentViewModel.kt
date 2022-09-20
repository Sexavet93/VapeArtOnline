package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vapeart.data.repositories.RoomRepository
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.SelectedItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(private var roomRepo: RoomRepository): ViewModel() {

    lateinit var favoriteItemLiveData: LiveData<FavoriteItem>
    lateinit var selectedItemLiveData: LiveData<SelectedItem>

    fun getFavoriteItem(id: String){
        favoriteItemLiveData = roomRepo.getFavoriteItem(id)
    }

    fun getSelectedItem(id: String){
        selectedItemLiveData = roomRepo.getSelectedItem(id)
    }

    fun addFavoriteItem(item: FavoriteItem){
        roomRepo.addFavoriteItem(item)
    }

    fun deleteFavoriteItem(item: FavoriteItem){
        roomRepo.deleteFavoriteItem(item)
    }
    fun addSelectedItem(item: SelectedItem){
        roomRepo.addSelectedItem(item)
    }
}