package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.vapeart.data.Firebase
import com.vapeart.data.repositories.FirestoreRepository
import com.vapeart.domain.Item

class HomeFragmentViewModel: ViewModel() {

    private val firestoreRepo: FirestoreRepository = FirestoreRepository.getInstance()

    private var _bestSellersResponseLiveData: LiveData<List<Item>> = firestoreRepo.getBestSellersLiveData()
    val bestSellersResponseLiveData : LiveData<List<Item>>
    get() = _bestSellersResponseLiveData

    private var _newItemsResponseLiveData: LiveData<List<Item>> = firestoreRepo.getNewItemList()
    val newItemsResponseLiveData : LiveData<List<Item>>
        get() = _newItemsResponseLiveData

    private var _discountsResponseLiveData: LiveData<List<Item>> = firestoreRepo.getDiscountsList()
    val discountsResponseLiveData : LiveData<List<Item>>
        get() = _discountsResponseLiveData

}