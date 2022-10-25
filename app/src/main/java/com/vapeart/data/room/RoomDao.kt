package com.vapeart.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vapeart.domain.models.FavoriteItem
import com.vapeart.domain.models.SelectedItem

@Dao
interface RoomDao {

    @Query("SELECT * FROM selecteditementity")
    fun getSelectedItems(): LiveData<List<SelectedItem>>

    @Query("SELECT * FROM favoriteitementity")
    fun getFavoriteItems(): LiveData<List<FavoriteItem>>

    @Query("SELECT * FROM selecteditementity WHERE id=(:id)")
    fun getSelectedItem(id: String): LiveData<SelectedItem>

    @Query("DELETE FROM selecteditementity")
    suspend fun deleteTable()

    @Query("SELECT * FROM favoriteitementity WHERE id=(:id)")
    fun getFavoriteItem(id: String): LiveData<FavoriteItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSelectedItem(item: SelectedItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteItem(item: FavoriteItemEntity)

    @Delete
    suspend fun deleteSelectedItem(item: SelectedItemEntity)

    @Delete
    suspend fun deleteFavoriteItem(item: FavoriteItemEntity)
}