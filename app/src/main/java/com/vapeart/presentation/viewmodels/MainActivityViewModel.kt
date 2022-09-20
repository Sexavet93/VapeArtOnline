package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.vapeart.data.Firebase
import com.vapeart.data.repositories.RoomRepository
import com.vapeart.data.repositories.SignInRepository
import com.vapeart.data.room.SelectedItem

class MainActivityViewModel: ViewModel() {

    private val firebaseAuthRepo: SignInRepository = SignInRepository.getInstance()
    private val roomRepo: RoomRepository = RoomRepository.getInstance()
    val selectedItemLiveData: LiveData<List<SelectedItem>> = roomRepo.getSelectedItems()

    fun isUserRegistered(): Boolean{
        return firebaseAuthRepo.isUserRegistered()
    }

    fun signOut(){
        val firebaseAuth: FirebaseAuth = Firebase.getInstance().firebaseAuth
        firebaseAuth.signOut()
    }
}