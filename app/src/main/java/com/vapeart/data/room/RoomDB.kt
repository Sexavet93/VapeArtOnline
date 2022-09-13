package com.vapeart.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val ROOM_DB_NAME = "room_database"

@Database(entities = [SelectedItem::class, FavoriteItem::class], version = 1, exportSchema = false)
abstract class RoomDB: RoomDatabase() {

    abstract fun getDao(): RoomDao

}