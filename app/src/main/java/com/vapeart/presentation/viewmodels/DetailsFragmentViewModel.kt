package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vapeart.domain.models.FavoriteItem
import com.vapeart.domain.models.SelectedItem
import com.vapeart.domain.repositories.RoomRepository
import com.vapeart.domain.usecases.local_db_usecases.favorite_items_usecases.AddFavoriteItemUseCase
import com.vapeart.domain.usecases.local_db_usecases.favorite_items_usecases.DeleteFavoriteItemUseCase
import com.vapeart.domain.usecases.local_db_usecases.favorite_items_usecases.GetFavoriteItemUseCase
import com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases.AddSelectedItemUseCase
import com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases.GetSelectedItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(roomRepo: RoomRepository): ViewModel() {

    lateinit var favoriteItemLiveData: LiveData<FavoriteItem>
    lateinit var selectedItemLiveData: LiveData<SelectedItem>

    private val getFavoriteItemUseCase: GetFavoriteItemUseCase = GetFavoriteItemUseCase(roomRepo)
    private val getSelectedItemItemUseCase: GetSelectedItemUseCase = GetSelectedItemUseCase(roomRepo)
    private val addFavoriteItemUseCase: AddFavoriteItemUseCase = AddFavoriteItemUseCase(roomRepo)
    private val deleteFavoriteItemUseCase: DeleteFavoriteItemUseCase = DeleteFavoriteItemUseCase(roomRepo)
    private val addSelectedItemUseCase: AddSelectedItemUseCase = AddSelectedItemUseCase(roomRepo)

    fun getFavoriteItem(id: String){
        favoriteItemLiveData = getFavoriteItemUseCase(id)
    }

    fun getSelectedItem(id: String){
        selectedItemLiveData = getSelectedItemItemUseCase(id)
    }

    fun addFavoriteItem(item: FavoriteItem){
        addFavoriteItemUseCase(item)
    }

    fun deleteFavoriteItem(item: FavoriteItem){
        deleteFavoriteItemUseCase(item)
    }
    fun addSelectedItem(item: SelectedItem){
        addSelectedItemUseCase(item)
    }
}