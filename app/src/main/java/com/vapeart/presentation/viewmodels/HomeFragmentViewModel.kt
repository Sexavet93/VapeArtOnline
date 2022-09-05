package com.vapeart.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.vapeart.data.Firebase
import com.vapeart.data.repositories.FirestoreRepository
import com.vapeart.data.repositories.RoomRepository
import com.vapeart.data.room.FavoriteItem
import com.vapeart.domain.Item

class HomeFragmentViewModel: ViewModel() {

    private val firestoreRepo: FirestoreRepository = FirestoreRepository.getInstance()
    private val roomRepo: RoomRepository = RoomRepository.getInstance()

    val bestSellersResponseLiveData : LiveData<List<Item>> = firestoreRepo.getBestSellersLiveData()
    val newItemsResponseLiveData : LiveData<List<Item>> = firestoreRepo.getNewItemList()
    val discountsResponseLiveData : LiveData<List<Item>> = firestoreRepo.getDiscountsList()
//    val favoriteItemLiveData: LiveData<FavoriteItem>

}