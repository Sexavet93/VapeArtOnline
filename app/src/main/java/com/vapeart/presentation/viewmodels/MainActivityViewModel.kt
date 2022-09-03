package com.vapeart.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.vapeart.data.Firebase
import com.vapeart.data.repositories.SignInRepository

class MainActivityViewModel: ViewModel() {

    private val firebaseAuthRepo: SignInRepository = SignInRepository.getInstance()

    fun isUserRegistered(): Boolean{
        return firebaseAuthRepo.isUserRegistered()
    }

    fun signOut(){
        val firebaseAuth: FirebaseAuth = Firebase.getInstance().firebaseAuth
        firebaseAuth.signOut()
    }
}