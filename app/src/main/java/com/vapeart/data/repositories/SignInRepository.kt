package com.vapeart.data.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
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

    fun signIn(email: String ,password: String, callback: (Boolean, String) -> Unit){
        coroutineScope.launch {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    task.exception?.message?.let { message ->
                        callback.invoke(task.isSuccessful,message)
                    } ?: callback.invoke(task.isSuccessful, "")
                }
        }
    }

    fun sendPasswordResetEmail(email:String, callback: (Boolean, String) -> Unit){
        coroutineScope.launch {
            firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    task.exception?.message?.let { message ->
                        callback.invoke(task.isSuccessful,message)
                    } ?: callback.invoke(task.isSuccessful, "")
                }
        }
    }
}