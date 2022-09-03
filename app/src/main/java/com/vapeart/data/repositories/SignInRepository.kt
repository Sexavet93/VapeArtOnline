package com.vapeart.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.vapeart.data.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInRepository private constructor() {

    private val firebaseAuth: FirebaseAuth = Firebase.getInstance().firebaseAuth
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun isUserRegistered(): Boolean{
        return firebaseAuth.currentUser != null
    }

    fun signIn(email: String ,password: String, callback: (Boolean) -> Unit){
        coroutineScope.launch {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    callback.invoke(task.isSuccessful)
                }
        }
    }

    companion object{
        private var INSTANCE: SignInRepository? = null

        fun getInstance(): SignInRepository{
            if(INSTANCE == null) INSTANCE = SignInRepository()
            INSTANCE?.let { return it } ?: throw RuntimeException("INSTANCE is null")
        }
    }
}