package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vapeart.domain.FirestoreRepository
import com.vapeart.domain.Item
import com.vapeart.domain.remote_db_usecases.GetBestSellersUseCase
import com.vapeart.domain.remote_db_usecases.GetDiscountsUseCase
import com.vapeart.domain.remote_db_usecases.GetNewItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(firestoreRepo: FirestoreRepository): ViewModel() {

    private val getBestSellersUseCase: GetBestSellersUseCase = GetBestSellersUseCase(firestoreRepo)
    private val getNewItemsUseCase: GetNewItemsUseCase = GetNewItemsUseCase(firestoreRepo)
    private val getDiscountsUseCase: GetDiscountsUseCase = GetDiscountsUseCase(firestoreRepo)

    val bestSellersResponseLiveData : LiveData<List<Item>> = getBestSellersUseCase.getBestSellers()
    val newItemsResponseLiveData : LiveData<List<Item>> = getNewItemsUseCase.getNewItems()
    val discountsResponseLiveData : LiveData<List<Item>> = getDiscountsUseCase.getDiscounts()

}