package com.vapeart.domain.usecases.remote_db_usecases

import com.vapeart.domain.models.Item
import com.vapeart.domain.repositories.FirestoreRepository

class GetSearchItemsUseCase(private val firestoreRepo: FirestoreRepository) {

    fun getSearchItems(query: String, callback:(List<Item>) -> Unit){
        firestoreRepo.getSearchItems(query,callback)
    }
}