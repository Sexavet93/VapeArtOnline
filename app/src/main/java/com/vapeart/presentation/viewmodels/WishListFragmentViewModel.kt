package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.SelectedItem
import com.vapeart.domain.repositories.RoomRepository
import com.vapeart.domain.usecases.local_db_usecases.favorite_items_usecases.DeleteFavoriteItemUseCase
import com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases.AddSelectedItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WishListFragmentViewModel @Inject constructor(private var roomRepo: RoomRepository): ViewModel() {

    private val addSelectedItemUseCase: AddSelectedItemUseCase = AddSelectedItemUseCase(roomRepo)
    private val deleteFavoriteItemUseCase: DeleteFavoriteItemUseCase = DeleteFavoriteItemUseCase(roomRepo)
    val favoriteItemsLiveData: LiveData<List<FavoriteItem>> = roomRepo.getFavoriteItems()

    fun addSelectedItem(item: SelectedItem){
        addSelectedItemUseCase.addSelectedItem(item)
    }

    fun deleteFavoriteItem(item: FavoriteItem){
        deleteFavoriteItemUseCase.deleteFavoriteItem(item)
    }
}