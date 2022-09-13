package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vapeart.data.repositories.FirestoreRepository
import com.vapeart.domain.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(firestoreRepo: FirestoreRepository): ViewModel() {

    val bestSellersResponseLiveData : LiveData<List<Item>> = firestoreRepo.bestSellersLiveData
    val newItemsResponseLiveData : LiveData<List<Item>> = firestoreRepo.newItemLiveData
    val discountsResponseLiveData : LiveData<List<Item>> = firestoreRepo.discountsLiveData

}