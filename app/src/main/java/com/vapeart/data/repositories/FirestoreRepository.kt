package com.vapeart.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.vapeart.data.Firebase
import com.vapeart.domain.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirestoreRepository private constructor() {

    private val firebaseCloud: FirebaseFirestore = Firebase.getInstance().firebaseCloud
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val bestSellersLiveData: MutableLiveData<List<Item>> = MutableLiveData()
    private val newItemLiveData: MutableLiveData<List<Item>> = MutableLiveData()
    private val discountsLiveData: MutableLiveData<List<Item>> = MutableLiveData()
    private val referencesList = listOf(
        firebaseCloud.collection("devices"),
        firebaseCloud.collection("atomizers"),
        firebaseCloud.collection("cartridges_and_coils"),
        firebaseCloud.collection("disposable_devices"),
        firebaseCloud.collection("regular_liquids"),
        firebaseCloud.collection("salt_nicotine_liquids")
    )

    init {
        getBestSellers()
        getNewItems()
        getDiscounts()
    }

    private fun getBestSellers() {
        val bestSellersList: MutableList<Item> = mutableListOf()
        for (reference in referencesList) {
            coroutineScope.launch {
                reference.whereGreaterThan("saleQuantityPerMonth", 7).get()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            for (item in task.result) {
                                val myItem = item.toObject(Item::class.java)
                                myItem.id = item.id
                                myItem.description = myItem.description.replace("\\n", "\n")
                                bestSellersList.add(myItem)
                                bestSellersLiveData.postValue(bestSellersList)
                            }
                        }
                    }
            }
        }
    }

    private fun getNewItems() {
        val newItemList: MutableList<Item> = mutableListOf()
        for (reference in referencesList) {
            coroutineScope.launch {
                reference.whereEqualTo("itemNew", true).get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (item in task.result) {
                            val myItem = item.toObject(Item::class.java)
                            myItem.id = item.id
                            myItem.description = myItem.description.replace("\\n", "\n")
                            newItemList.add(myItem)
                            newItemLiveData.postValue(newItemList)
                        }
                    }
                }
            }
        }
    }

    private fun getDiscounts() {
        val discountsList: MutableList<Item> = mutableListOf()
        for (reference in referencesList) {
            coroutineScope.launch {
                reference.whereNotEqualTo("oldPrice", "0").get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (item in task.result) {
                            val myItem = item.toObject(Item::class.java)
                            myItem.id = item.id
                            myItem.description = myItem.description.replace("\\n", "\n")
                            discountsList.add(myItem)
                            discountsLiveData.postValue(discountsList)
                        }
                    }
                }
            }
        }
    }

    fun getBestSellersLiveData(): LiveData<List<Item>>{
        return bestSellersLiveData
    }

    fun getNewItemList(): LiveData<List<Item>>{
        return newItemLiveData
    }

    fun getDiscountsList(): LiveData<List<Item>>{
        return discountsLiveData
    }

    companion object {
        private var INSTANCE: FirestoreRepository? = null

        fun getInstance(): FirestoreRepository {
            if (INSTANCE == null)
                INSTANCE = FirestoreRepository()
            INSTANCE?.let {
                return it
            } ?: throw RuntimeException("INSTANCE is null")
        }
    }
}