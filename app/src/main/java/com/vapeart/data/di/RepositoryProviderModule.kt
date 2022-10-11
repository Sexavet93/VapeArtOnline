package com.vapeart.data.di

import com.vapeart.data.repositories.FirestoreRepositoryImpl
import com.vapeart.data.repositories.RoomRepositoryImpl
import com.vapeart.data.repositories.SignInRepositoryImpl
import com.vapeart.data.repositories.SignUpRepositoryImpl
import com.vapeart.domain.repositories.FirestoreRepository
import com.vapeart.domain.repositories.RoomRepository
import com.vapeart.domain.repositories.SignInRepository
import com.vapeart.domain.repositories.SignUpRepository
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
    fun getFirestoreRepository(repositoryImpl: FirestoreRepositoryImpl): FirestoreRepository

    @Binds
    @Singleton
    fun getRoomRepository(repositoryImpl: RoomRepositoryImpl): RoomRepository

    @Binds
    @Singleton
    fun getSignInRepository(repositoryImpl: SignInRepositoryImpl): SignInRepository

    @Binds
    @Singleton
    fun getSignUpRepository(repositoryImpl: SignUpRepositoryImpl): SignUpRepository

}