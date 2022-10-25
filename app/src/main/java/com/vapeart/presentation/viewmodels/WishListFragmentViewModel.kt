package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vapeart.domain.models.FavoriteItem
import com.vapeart.domain.models.SelectedItem
import com.vapeart.domain.repositories.RoomRepository
import com.vapeart.domain.usecases.local_db_usecases.favorite_items_usecases.DeleteFavoriteItemUseCase
import com.vapeart.domain.usecases.local_db_usecases.favorite_items_usecases.GetFavoriteItemsUseCase
import com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases.AddSelectedItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WishListFragmentViewModel @Inject constructor(roomRepo: RoomRepository): ViewModel() {

    private val addSelectedItemUseCase: AddSelectedItemUseCase = AddSelectedItemUseCase(roomRepo)
    private val deleteFavoriteItemUseCase: DeleteFavoriteItemUseCase = DeleteFavoriteItemUseCase(roomRepo)
    private val getFavoriteItemsUseCase: GetFavoriteItemsUseCase = GetFavoriteItemsUseCase(roomRepo)
    val favoriteItemsLiveData: LiveData<List<FavoriteItem>> = getFavoriteItemsUseCase()

    fun addSelectedItem(item: SelectedItem){
        addSelectedItemUseCase(item)
    }

    fun deleteFavoriteItem(item: FavoriteItem){
        deleteFavoriteItemUseCase(item)
    }
}