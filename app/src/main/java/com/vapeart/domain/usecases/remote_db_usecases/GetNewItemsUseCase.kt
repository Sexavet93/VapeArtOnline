package com.vapeart.domain.usecases.remote_db_usecases

import androidx.lifecycle.MutableLiveData
import com.vapeart.domain.repositories.FirestoreRepository
import com.vapeart.domain.models.Item

class GetNewItemsUseCase(private val firestoreRepo: FirestoreRepository) {

    operator fun invoke(): MutableLiveData<List<Item>>{
        return firestoreRepo.getNewItems()
    }
}