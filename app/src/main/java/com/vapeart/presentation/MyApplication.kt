package com.vapeart.presentation

import android.app.Application
import com.vapeart.data.Firebase
import com.vapeart.data.repositories.FirestoreRepository
import com.vapeart.data.repositories.RoomRepository

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        val appContext = applicationContext
        Firebase.initializeFirebase(appContext)
        FirestoreRepository.getInstance()
        RoomRepository.initialize(appContext)
    }
}