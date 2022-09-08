package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vapeart.data.repositories.FirestoreRepository
import com.vapeart.domain.Item

class ItemsReviewFragmentViewModel : ViewModel() {

    val firebaseRepo: FirestoreRepository = FirestoreRepository.getInstance()
    private var _queryLiveData: MutableLiveData<List<Item>> = MutableLiveData()
    val queryLiveData: LiveData<List<Item>>
        get() = _queryLiveData

    fun getQueryItems(query: String){
        _queryLiveData = firebaseRepo.getQueryItemsList(query)
    }
}