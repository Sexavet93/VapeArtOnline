package com.vapeart.domain

import androidx.lifecycle.LiveData
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.SelectedItem

interface RoomRepository {

    fun getSelectedItems(): LiveData<List<SelectedItem>>

    fun getFavoriteItems(): LiveData<List<FavoriteItem>>

    fun getSelectedItem(id: String): LiveData<SelectedItem>

    fun deleteTable()

    fun getFavoriteItem(id: String): LiveData<FavoriteItem>

    fun addSelectedItem(item: SelectedItem)

    fun addFavoriteItem(item: FavoriteItem)

    fun deleteSelectedItem(item: SelectedItem)

    fun deleteFavoriteItem(item: FavoriteItem)
}