package com.vapeart.domain.remote_db_usecases

import androidx.lifecycle.MutableLiveData
import com.vapeart.domain.FirestoreRepository
import com.vapeart.domain.Item

class GetNewItemsUseCase(private val firestoreRepo: FirestoreRepository) {

    fun getNewItems(): MutableLiveData<List<Item>>{
        return firestoreRepo.getNewItems()
    }
}