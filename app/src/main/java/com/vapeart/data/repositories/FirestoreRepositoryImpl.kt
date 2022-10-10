package com.vapeart.data.repositories

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.vapeart.domain.repositories.FirestoreRepository
import com.vapeart.domain.models.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor(private var firebaseCloud: FirebaseFirestore)
    : FirestoreRepository {

    private val referencesList: List<CollectionReference> = listOf(
        firebaseCloud.collection(DEVICES_PATH),
        firebaseCloud.collection(ATOMIZERS_PATH),
        firebaseCloud.collection(CARTRIDGES_PATH),
        firebaseCloud.collection(DISPOSABLE_DEVICES_PATH),
        firebaseCloud.collection(REGULAR_LIQUIDS_PATH),
        firebaseCloud.collection(SALT_NICOTINE_PATH)
    )
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)


    fun getReferencesListMet() = referencesList

    override fun getBestSellers(): MutableLiveData<List<Item>> {
        val bestSellersList: MutableList<Item> = mutableListOf()
        val liveData = MutableLiveData<List<Item>>()
        for (reference in referencesList) {
            coroutineScope.launch {
                reference.whereGreaterThan(BEST_SELLERS_SORT_KEY, BEST_SELLERS_MIN_VALUE).get()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            for (item in task.result) {
                                val myItem = item.toObject(Item::class.java)
                                myItem.id = item.id
                                myItem.description = myItem.description.replace("\\n", "\n")
                                bestSellersList.add(myItem)
                            }
                            liveData.postValue(bestSellersList)
                        }
                    }
            }
        }
        return liveData
    }

    override fun getNewItems(): MutableLiveData<List<Item>> {
        val newItemList: MutableList<Item> = mutableListOf()
        val liveData = MutableLiveData<List<Item>>()
        for (reference in referencesList) {
            coroutineScope.launch {
                reference.whereEqualTo(NEW_ITEM_SORT_KEY, IS_NEW_ITEM).get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (item in task.result) {
                            val myItem = item.toObject(Item::class.java)
                            myItem.id = item.id
                            myItem.description = myItem.description.replace("\\n", "\n")
                            newItemList.add(myItem)
                        }
                        liveData.postValue(newItemList)
                    }
                }
            }
        }
        return liveData
    }

    override fun getDiscounts(): MutableLiveData<List<Item>> {
        val discountsList: MutableList<Item> = mutableListOf()
        val liveData = MutableLiveData<List<Item>>()
        for (reference in referencesList) {
            coroutineScope.launch {
                reference.whereNotEqualTo(DISCOUNT_SORT_KEY, OLD_PRICE_DEFAULT_VALUE).get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (item in task.result) {
                            val myItem = item.toObject(Item::class.java)
                            myItem.id = item.id
                            myItem.description = myItem.description.replace("\\n", "\n")
                            discountsList.add(myItem)
                        }
                        liveData.postValue(discountsList)
                    }
                }
            }
        }
        return liveData
    }

    override fun getQueryItemsList(query: String): MutableLiveData<List<Item>> {
        val liveData: MutableLiveData<List<Item>> = MutableLiveData()
        val itemList = mutableListOf<Item>()
        coroutineScope.launch {
            firebaseCloud.collection(query).get().addOnCompleteListener { task ->
                if(task.isSuccessful){
                    task.result.forEach {
                        val item = it.toObject(Item::class.java)
                        item.id = it.id
                        item.description = item.description.replace("\\n", "\n")
                        itemList.add(item)
                    }
                    liveData.postValue(itemList)
                }
            }
        }
        return liveData
    }

    override fun getSearchItems(query: String, callback: (List<Item>) -> Unit){
        val regex = query.toRegex(RegexOption.IGNORE_CASE)
        val itemList = mutableListOf<Item>()
        referencesList.forEach{ reference ->
            coroutineScope.launch {
                reference.get().addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        task.result.forEach {
                            coroutineScope.launch {
                                val name = it.data["name"] as String
                                if(regex.containsMatchIn(name)){
                                    val item = it.toObject(Item::class.java)
                                    item.id = it.id
                                    item.description = item.description.replace("\\n", "\n")
                                    itemList.add(item)
                                    callback.invoke(itemList)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    companion object{
         const val DEVICES_PATH = "devices"
         const val ATOMIZERS_PATH = "atomizers"
         const val CARTRIDGES_PATH = "cartridges_and_coils"
         const val DISPOSABLE_DEVICES_PATH = "disposable_devices"
         const val REGULAR_LIQUIDS_PATH = "regular_liquids"
         const val SALT_NICOTINE_PATH = "salt_nicotine_liquid"
         const val BEST_SELLERS_SORT_KEY = "saleQuantityPerMonth"
         const val NEW_ITEM_SORT_KEY = "itemNew"
         const val DISCOUNT_SORT_KEY = "oldPrice"
         const val OLD_PRICE_DEFAULT_VALUE = "0"
         const val BEST_SELLERS_MIN_VALUE = 7
         const val IS_NEW_ITEM = true
    }

}