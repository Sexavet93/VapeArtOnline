package com.vapeart.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

const val ROOM_DB_NAME = "room_database"

@Database(entities = [SelectedItemEntity::class, FavoriteItemEntity::class], version = 1, exportSchema = false)
abstract class RoomDB: RoomDatabase() {

    abstract fun getDao(): RoomDao

}