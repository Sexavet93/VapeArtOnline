package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vapeart.data.room.SelectedItem
import com.vapeart.domain.repositories.RoomRepository
import com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases.AddSelectedItemUseCase
import com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases.DeleteSelectedItemUseCase
import com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases.DeleteSelectedItemsTableUseCase
import com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases.GetSelectedItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartFragmentViewModel @Inject constructor(roomRepository: RoomRepository): ViewModel() {

    private val getSelectedItemsUseCase: GetSelectedItemsUseCase = GetSelectedItemsUseCase(roomRepository)
    private val deleteSelectedItemUseCase: DeleteSelectedItemUseCase = DeleteSelectedItemUseCase(roomRepository)
    private val addSelectedItemUseCase: AddSelectedItemUseCase = AddSelectedItemUseCase(roomRepository)
    private val deleteSelectedItemsTableUseCase: DeleteSelectedItemsTableUseCase = DeleteSelectedItemsTableUseCase(roomRepository)

    val selectedItemsLiveData: LiveData<List<SelectedItem>> = getSelectedItemsUseCase()

    fun deleteSelectedItem(item: SelectedItem){
        deleteSelectedItemUseCase(item)
    }

    fun addSelectedItem(item: SelectedItem){
        addSelectedItemUseCase(item)
    }

    fun deleteAllItems(){
        deleteSelectedItemsTableUseCase()
    }

    override fun onCleared() {
        super.onCleared()
    }
}