package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.SelectedItem
import com.vapeart.domain.repositories.RoomRepository
import com.vapeart.domain.usecases.local_db_usecases.favorite_items_usecases.AddFavoriteItemUseCase
import com.vapeart.domain.usecases.local_db_usecases.favorite_items_usecases.DeleteFavoriteItemUseCase
import com.vapeart.domain.usecases.local_db_usecases.favorite_items_usecases.GetFavoriteItemUseCase
import com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases.AddSelectedItemUseCase
import com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases.GetSelectedItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(private var roomRepo: RoomRepository): ViewModel() {

    lateinit var favoriteItemLiveData: LiveData<FavoriteItem>
    lateinit var selectedItemLiveData: LiveData<SelectedItem>

    private val getFavoriteItemUseCase: GetFavoriteItemUseCase = GetFavoriteItemUseCase(roomRepo)
    private val getSelectedItemItemUseCase: GetSelectedItemUseCase = GetSelectedItemUseCase(roomRepo)
    private val addFavoriteItemUseCase: AddFavoriteItemUseCase = AddFavoriteItemUseCase(roomRepo)
    private val deleteFavoriteItemUseCase: DeleteFavoriteItemUseCase = DeleteFavoriteItemUseCase(roomRepo)
    private val addSelectedItemUseCase: AddSelectedItemUseCase = AddSelectedItemUseCase(roomRepo)

    fun getFavoriteItem(id: String){
        favoriteItemLiveData = getFavoriteItemUseCase.getFavoriteItem(id)
    }

    fun getSelectedItem(id: String){
        selectedItemLiveData = getSelectedItemItemUseCase.getSelectedItem(id)
    }

    fun addFavoriteItem(item: FavoriteItem){
        addFavoriteItemUseCase.addFavoriteItem(item)
    }

    fun deleteFavoriteItem(item: FavoriteItem){
        deleteFavoriteItemUseCase.deleteFavoriteItem(item)
    }
    fun addSelectedItem(item: SelectedItem){
        addSelectedItemUseCase.addSelectedItem(item)
    }
}