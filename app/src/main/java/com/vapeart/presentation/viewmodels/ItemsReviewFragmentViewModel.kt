package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vapeart.domain.models.Item
import com.vapeart.domain.repositories.FirestoreRepository
import com.vapeart.domain.usecases.remote_db_usecases.GetQueryItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemsReviewFragmentViewModel @Inject constructor(private val firestoreRepo: FirestoreRepository)
    : ViewModel() {

    private val getQueryItemsUseCase: GetQueryItemsUseCase = GetQueryItemsUseCase(firestoreRepo)
    private var _queryLiveData: MutableLiveData<List<Item>> = MutableLiveData()
    val queryLiveData: LiveData<List<Item>>
        get() = _queryLiveData

    fun getQueryItems(query: String){
        _queryLiveData = getQueryItemsUseCase.getQueryItemsList(query)
    }
}