package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vapeart.data.repositories.FirestoreRepository
import com.vapeart.domain.Item

class HomeFragmentViewModel: ViewModel() {

    private val firestoreRepo: FirestoreRepository = FirestoreRepository.getInstance()

    val bestSellersResponseLiveData : LiveData<List<Item>> = firestoreRepo.bestSellersLiveData
    val newItemsResponseLiveData : LiveData<List<Item>> = firestoreRepo.newItemLiveData
    val discountsResponseLiveData : LiveData<List<Item>> = firestoreRepo.discountsLiveData



}