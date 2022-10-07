package com.vapeart.domain

import androidx.lifecycle.MutableLiveData

interface FirestoreRepository {

    fun getBestSellers(): MutableLiveData<List<Item>>

    fun getNewItems(): MutableLiveData<List<Item>>

    fun getDiscounts(): MutableLiveData<List<Item>>

    fun getQueryItemsList(query: String): MutableLiveData<List<Item>>
}