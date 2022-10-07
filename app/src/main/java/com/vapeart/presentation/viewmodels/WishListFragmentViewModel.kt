package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vapeart.data.repositories.RoomRepositoryImpl
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.SelectedItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WishListFragmentViewModel @Inject constructor(private var roomRepo: RoomRepositoryImpl): ViewModel() {

    val favoriteItemsLiveData: LiveData<List<FavoriteItem>> = roomRepo.getFavoriteItems()

    fun addSelectedItem(item: SelectedItem){
        roomRepo.addSelectedItem(item)
    }

    fun deleteFavoriteItem(item: FavoriteItem){
        roomRepo.deleteFavoriteItem(item)
    }
}