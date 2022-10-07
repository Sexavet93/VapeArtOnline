package com.vapeart.domain.remote_db_usecases

import androidx.lifecycle.MutableLiveData
import com.vapeart.domain.FirestoreRepository
import com.vapeart.domain.Item

class GetBestSellersUseCase(private val firestoreRepo: FirestoreRepository) {

    fun getBestSellers(): MutableLiveData<List<Item>>{
        return firestoreRepo.getBestSellers()
    }
}