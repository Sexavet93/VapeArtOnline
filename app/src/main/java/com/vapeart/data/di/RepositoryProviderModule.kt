package com.vapeart.data.di

import com.vapeart.data.repositories.FirestoreRepositoryImpl
import com.vapeart.data.repositories.RoomRepositoryImpl
import com.vapeart.domain.FirestoreRepository
import com.vapeart.domain.RoomRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryProviderModule {

    @Binds
    @Singleton
    abstract fun getFirestoreRepository(repositoryImpl: FirestoreRepositoryImpl): FirestoreRepository

    @Binds
    @Singleton
    abstract fun getRoomRepository(repositoryImpl: RoomRepositoryImpl): RoomRepository
}