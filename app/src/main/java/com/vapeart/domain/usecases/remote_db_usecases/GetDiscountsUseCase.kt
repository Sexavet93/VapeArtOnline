package com.vapeart.domain.usecases.remote_db_usecases

import androidx.lifecycle.MutableLiveData
import com.vapeart.domain.repositories.FirestoreRepository
import com.vapeart.domain.models.Item

class GetDiscountsUseCase(private val firestoreRepo: FirestoreRepository) {

    fun getDiscounts(): MutableLiveData<List<Item>>{
        return firestoreRepo.getDiscounts()
    }
}