package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vapeart.domain.models.Item
import com.vapeart.domain.repositories.FirestoreRepository
import com.vapeart.domain.usecases.remote_db_usecases.GetSearchItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(private val firebaseRepo: FirestoreRepository): ViewModel() {

    private val getSearchItemsUseCase: GetSearchItemsUseCase = GetSearchItemsUseCase(firebaseRepo)
    private var _requestItemList: MutableLiveData<List<Item>> = MutableLiveData()
    val requestItemList: LiveData<List<Item>>
        get() = _requestItemList

    fun getSearchItems(query: String){
        getSearchItemsUseCase.getSearchItems(query){
            _requestItemList.postValue(it)
        }
    }
}