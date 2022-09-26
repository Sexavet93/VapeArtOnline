package com.vapeart.data.repositories

import androidx.lifecycle.LiveData
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.RoomDao
import com.vapeart.data.room.SelectedItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomRepository @Inject constructor(private var dao:RoomDao) {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun getSelectedItems(): LiveData<List<SelectedItem>> {
        return dao.getSelectedItems()
    }

    fun getFavoriteItems(): LiveData<List<FavoriteItem>>{
        return dao.getFavoriteItems()
    }

    fun getSelectedItem(id: String): LiveData<SelectedItem>{
        return dao.getSelectedItem(id)
    }

    fun getFavoriteItem(id: String): LiveData<FavoriteItem> {
        return dao.getFavoriteItem(id)
    }

    fun addSelectedItem(item: SelectedItem){
        coroutineScope.launch { dao.addSelectedItem(item) }
    }

    fun addFavoriteItem(item: FavoriteItem){
        coroutineScope.launch { dao.addFavoriteItem(item) }
    }

    fun deleteSelectedItem(item: SelectedItem){
        coroutineScope.launch { dao.deleteSelectedItem(item) }
    }

    fun deleteFavoriteItem(item: FavoriteItem){
        coroutineScope.launch { dao.deleteFavoriteItem(item) }
    }

    fun deleteTable(){
        coroutineScope.launch { dao.deleteTable()}
    }
}