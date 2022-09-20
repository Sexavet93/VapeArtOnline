package com.vapeart.data.repositories

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.vapeart.domain.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirestoreRepository @Inject constructor(private var firebaseCloud: FirebaseFirestore) {

    private val referencesList = listOf(
        firebaseCloud.collection("devices"),
        firebaseCloud.collection("atomizers"),
        firebaseCloud.collection("cartridges_and_coils"),
        firebaseCloud.collection("disposable_devices"),
        firebaseCloud.collection("regular_liquids"),
        firebaseCloud.collection("salt_nicotine_liquids")
    )
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val bestSellersLiveData: MutableLiveData<List<Item>> = getBestSellers()
    val newItemLiveData: MutableLiveData<List<Item>> = getNewItems()
    val discountsLiveData: MutableLiveData<List<Item>> = getDiscounts()


    private fun getBestSellers(): MutableLiveData<List<Item>> {
        val bestSellersList: MutableList<Item> = mutableListOf()
        val liveData = MutableLiveData<List<Item>>()
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
                                liveData.postValue(bestSellersList)
                            }
                        }
                    }
            }
        }
        return liveData
    }

    private fun getNewItems(): MutableLiveData<List<Item>> {
        val newItemList: MutableList<Item> = mutableListOf()
        val liveData = MutableLiveData<List<Item>>()
        for (reference in referencesList) {
            coroutineScope.launch {
                reference.whereEqualTo("itemNew", true).get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (item in task.result) {
                            val myItem = item.toObject(Item::class.java)
                            myItem.id = item.id
                            myItem.description = myItem.description.replace("\\n", "\n")
                            newItemList.add(myItem)
                            liveData.postValue(newItemList)
                        }
                    }
                }
            }
        }
        return liveData
    }

    private fun getDiscounts(): MutableLiveData<List<Item>> {
        val discountsList: MutableList<Item> = mutableListOf()
        val liveData = MutableLiveData<List<Item>>()
        for (reference in referencesList) {
            coroutineScope.launch {
                reference.whereNotEqualTo("oldPrice", "0").get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (item in task.result) {
                            val myItem = item.toObject(Item::class.java)
                            myItem.id = item.id
                            myItem.description = myItem.description.replace("\\n", "\n")
                            discountsList.add(myItem)
                            liveData.postValue(discountsList)
                        }
                    }
                }
            }
        }
        return liveData
    }

    fun getQueryItemsList(query: String): MutableLiveData<List<Item>>{
        val queryLiveData: MutableLiveData<List<Item>> = MutableLiveData()
        val itemList = mutableListOf<Item>()
        coroutineScope.launch {
            firebaseCloud.collection(query).get().addOnCompleteListener { task ->
                if(task.isSuccessful){
                    task.result.forEach {
                        val item = it.toObject(Item::class.java)
                        item.id = it.id
                        item.description = item.description.replace("\\n", "\n")
                        itemList.add(item)
                        queryLiveData.postValue(itemList)
                    }
                }
            }
        }
        return queryLiveData
    }

    fun getReferencesList() = referencesList
}