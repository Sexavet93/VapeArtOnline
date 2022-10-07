package com.vapeart.domain.remote_db_usecases

import androidx.lifecycle.MutableLiveData
import com.vapeart.domain.FirestoreRepository
import com.vapeart.domain.Item

class GetQueryItemsUseCase(private val firestoreRepo: FirestoreRepository) {

    fun getQueryItemsList(query: String): MutableLiveData<List<Item>>{
        return firestoreRepo.getQueryItemsList(query)
    }
}