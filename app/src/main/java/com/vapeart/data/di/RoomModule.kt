package com.vapeart.data.di

import android.content.Context
import androidx.room.Room
import com.vapeart.data.repositories.RoomRepository
import com.vapeart.data.room.ROOM_DB_NAME
import com.vapeart.data.room.RoomDB
import com.vapeart.data.room.RoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun getRoomDb(@ApplicationContext context: Context): RoomDB {
        return Room.databaseBuilder(
            context.applicationContext,
            RoomDB::class.java,
            ROOM_DB_NAME
        ).build()
    }

    @Provides
    fun getDao(roomDb: RoomDB): RoomDao {
        return roomDb.getDao()
    }

}