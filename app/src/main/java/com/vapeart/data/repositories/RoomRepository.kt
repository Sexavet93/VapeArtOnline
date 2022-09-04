package com.vapeart.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.RoomDB
import com.vapeart.data.room.SelectedItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val ROOM_DB_NAME = "room_database"

class RoomRepository private constructor(context: Context) {

    private val roomDB = Room.databaseBuilder(context, RoomDB::class.java, ROOM_DB_NAME).build()
    private val dao = roomDB.getDao()
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun getSelectedItems(): LiveData<List<SelectedItem>> {
        return dao.getSelectedItems()
    }

    fun getSelectedItem(id: String): LiveData<SelectedItem>{
        return dao.getSelectedItem(id)
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