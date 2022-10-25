package com.vapeart.domain.repositories

import androidx.lifecycle.LiveData
import com.vapeart.domain.models.FavoriteItem
import com.vapeart.domain.models.SelectedItem

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