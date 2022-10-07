package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vapeart.data.repositories.FirestoreRepositoryImpl
import com.vapeart.domain.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemsReviewFragmentViewModel @Inject constructor(private var firebaseRepo: FirestoreRepositoryImpl): ViewModel() {

    private var _queryLiveData: MutableLiveData<List<Item>> = MutableLiveData()
    val queryLiveData: LiveData<List<Item>>
        get() = _queryLiveData

    fun getQueryItems(query: String){
        _queryLiveData = firebaseRepo.getQueryItemsList(query)
    }
}