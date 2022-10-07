package com.vapeart.domain.remote_db_usecases

import androidx.lifecycle.MutableLiveData
import com.vapeart.domain.FirestoreRepository
import com.vapeart.domain.Item

class GetDiscountsUseCase(private val firestoreRepo: FirestoreRepository) {

    fun getDiscounts(): MutableLiveData<List<Item>>{
        return firestoreRepo.getDiscounts()
    }
}