package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.CollectionReference
import com.vapeart.data.repositories.FirestoreRepository
import com.vapeart.domain.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(firebaseRepo: FirestoreRepository): ViewModel() {

    private val referencesList: List<CollectionReference> = firebaseRepo.getReferencesList()
    private val _requestItemList: MutableLiveData<List<Item>> = MutableLiveData()
    val requestItemList: LiveData<List<Item>>
        get() = _requestItemList

    fun getRequestItems(query: String){
        val regex = query.toRegex(RegexOption.IGNORE_CASE)
        val itemList = mutableListOf<Item>()
        referencesList.forEach{ reference ->
            viewModelScope.launch(Dispatchers.IO) {
                reference.get().addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        task.result.forEach {
                            viewModelScope.launch {
                                val name = it.data["name"] as String
                                if(regex.containsMatchIn(name)){
                                    val item = it.toObject(Item::class.java)
                                    item.id = it.id
                                    item.description = item.description.replace("\\n", "\n")
                                    itemList.add(item)
                                    _requestItemList.postValue(itemList)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}