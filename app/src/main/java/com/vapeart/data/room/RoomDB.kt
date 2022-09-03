package com.vapeart.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SelectedItem::class], version = 1, exportSchema = false)
abstract class RoomDB: RoomDatabase() {

    abstract fun getDao(): RoomDao
}