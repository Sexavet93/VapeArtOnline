package com.vapeart.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val ROOM_DB_NAME = "room_database"

@Database(entities = [SelectedItem::class, FavoriteItem::class], version = 1, exportSchema = false)
abstract class RoomDB: RoomDatabase() {

    abstract fun getDao(): RoomDao

    companion object{
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(context: Context): RoomDB{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    ROOM_DB_NAME).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}