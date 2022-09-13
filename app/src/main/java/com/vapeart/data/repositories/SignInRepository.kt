package com.vapeart.data.repositories

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInRepository @Inject constructor(private var firebaseAuth: FirebaseAuth) {

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
}