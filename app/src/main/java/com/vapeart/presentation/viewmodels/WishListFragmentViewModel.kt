package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vapeart.data.repositories.RoomRepository
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.SelectedItem

class WishListFragmentViewModel: ViewModel() {

    val roomRepo: RoomRepository  = RoomRepository.getInstance()
    val favoriteItemsLiveData: LiveData<List<FavoriteItem>> = roomRepo.getFavoriteItems()

    fun addSelectedItem(item: SelectedItem){
        roomRepo.addSelectedItem(item)
    }

    fun deleteFavoriteItem(item: FavoriteItem){
        roomRepo.deleteFavoriteItem(item)
    }
}