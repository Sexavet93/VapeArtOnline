package com.vapeart.data.repositories

import androidx.lifecycle.LiveData
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.RoomDao
import com.vapeart.data.room.SelectedItem
import com.vapeart.domain.repositories.RoomRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(private var dao:RoomDao): RoomRepository {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    override fun getSelectedItems(): LiveData<List<SelectedItem>> {
        return dao.getSelectedItems()
    }

    override fun getFavoriteItems(): LiveData<List<FavoriteItem>>{
        return dao.getFavoriteItems()
    }

    override fun getSelectedItem(id: String): LiveData<SelectedItem>{
        return dao.getSelectedItem(id)
    }

    override fun getFavoriteItem(id: String): LiveData<FavoriteItem> {
        return dao.getFavoriteItem(id)
    }

    override fun addSelectedItem(item: SelectedItem){
        coroutineScope.launch { dao.addSelectedItem(item) }
    }

    override fun addFavoriteItem(item: FavoriteItem){
        coroutineScope.launch { dao.addFavoriteItem(item) }
    }

    override fun deleteSelectedItem(item: SelectedItem){
        coroutineScope.launch { dao.deleteSelectedItem(item) }
    }

    override fun deleteFavoriteItem(item: FavoriteItem){
        coroutineScope.launch { dao.deleteFavoriteItem(item) }
    }

    override fun deleteTable(){
        coroutineScope.launch { dao.deleteTable()}
    }
}