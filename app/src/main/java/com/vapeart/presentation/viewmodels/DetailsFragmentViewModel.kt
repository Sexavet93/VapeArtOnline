package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vapeart.data.repositories.RoomRepository
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.SelectedItem

class DetailsFragmentViewModel: ViewModel() {

    private val roomRepo = RoomRepository.getInstance()
    private var _favoriteItemLiveData: MutableLiveData<FavoriteItem> = MutableLiveData()
    lateinit var favoriteItemLiveData: LiveData<FavoriteItem>
//    get() = _favoriteItemLiveData

    fun getFavoriteItem(id: String){
        favoriteItemLiveData = roomRepo.getFavoriteItem(id)
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