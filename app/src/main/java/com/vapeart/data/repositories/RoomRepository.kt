package com.vapeart.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.RoomDB
import com.vapeart.data.room.SelectedItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomRepository private constructor(context: Context) {

    private val roomDB = RoomDB.getInstance(context)
    private val dao = roomDB.getDao()
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

    companion object{
        private var INSTANCE: RoomRepository? = null

        fun initialize(context: Context){
            if(INSTANCE == null) INSTANCE = RoomRepository(context)
        }

        fun getInstance(): RoomRepository{
            INSTANCE?.let { return it } ?: throw RuntimeException("RoomRepository must be initialized")
        }
    }
}