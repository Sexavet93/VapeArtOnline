package com.vapeart.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RoomDao {

    @Query("SELECT * FROM selecteditem")
    fun getSelectedItems(): LiveData<List<SelectedItem>>

    @Query("SELECT * FROM selecteditem WHERE id=(:id)")
    fun getSelectedItem(id: String): LiveData<SelectedItem>

    @Insert
    fun addSelectedItem(item: SelectedItem)

    @Insert
    fun addFavoriteItem(item: FavoriteItem)

    @Delete
    fun deleteSelectedItem(item: SelectedItem)

    @Delete
    fun deleteFavoriteItem(item: FavoriteItem)
}