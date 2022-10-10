package com.vapeart.domain.repositories

import androidx.lifecycle.MutableLiveData
import com.vapeart.domain.models.Item

interface FirestoreRepository {

    fun getBestSellers(): MutableLiveData<List<Item>>

    fun getNewItems(): MutableLiveData<List<Item>>

    fun getDiscounts(): MutableLiveData<List<Item>>

    fun getQueryItemsList(query: String): MutableLiveData<List<Item>>

    fun getSearchItems(query: String, callback:(List<Item>) -> Unit)
}