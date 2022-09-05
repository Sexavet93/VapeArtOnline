package com.vapeart.data.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface RoomDao {

    @Query("SELECT * FROM selecteditem")
    fun getSelectedItems(): LiveData<List<SelectedItem>>

    @Query("SELECT * FROM selecteditem WHERE id=(:id)")
    fun getSelectedItem(id: String): LiveData<SelectedItem>

    @Query("SELECT * FROM favoriteitem WHERE id=(:id)")
    fun getFavoriteItem(id: String): LiveData<FavoriteItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSelectedItem(item: SelectedItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteItem(item: FavoriteItem)

    @Delete
    suspend fun deleteSelectedItem(item: SelectedItem)

    @Delete
    suspend fun deleteFavoriteItem(item: FavoriteItem)
}