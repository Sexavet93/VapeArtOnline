package com.vapeart.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RoomDao {

    @Query("SELECT * FROM selecteditem")
    fun getItems(): LiveData<List<SelectedItem>>

    @Query("SELECT * FROM selecteditem WHERE id=(:id)")
    fun getItem(id: String): LiveData<SelectedItem>

    @Insert
    fun addItem(item: SelectedItem)

    @Delete
    fun deleteItem(item: SelectedItem)
}