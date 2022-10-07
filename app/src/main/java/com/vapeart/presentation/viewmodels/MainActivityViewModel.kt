package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.vapeart.data.repositories.RoomRepositoryImpl
import com.vapeart.data.repositories.SignInRepository
import com.vapeart.data.room.SelectedItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(roomRepo: RoomRepositoryImpl, ): ViewModel() {

    @Inject lateinit var firebaseAuth: FirebaseAuth
    @Inject lateinit var firebaseAuthRepo: SignInRepository
    val selectedItemLiveData: LiveData<List<SelectedItem>> = roomRepo.getSelectedItems()

    fun isUserRegistered(): Boolean{
        return firebaseAuthRepo.isUserRegistered()
    }

    fun signOut(){
        firebaseAuth.signOut()
    }
}