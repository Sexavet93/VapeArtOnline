package com.vapeart.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.vapeart.domain.repositories.SignInRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(private var firebaseAuth: FirebaseAuth)
    :SignInRepository{

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    override fun isUserRegistered(): Boolean{
        return firebaseAuth.currentUser != null
    }

    override fun signIn(email: String, password: String, callback: (Boolean, String) -> Unit){
        coroutineScope.launch {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    task.exception?.message?.let { message ->
                        callback.invoke(task.isSuccessful,message)
                    } ?: callback.invoke(task.isSuccessful, "")
                }
        }
    }

    override fun sendPasswordResetEmail(email:String, callback: (Boolean, String) -> Unit){
        coroutineScope.launch {
            firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    task.exception?.message?.let { message ->
                        callback.invoke(task.isSuccessful,message)
                    } ?: callback.invoke(task.isSuccessful, "")
                }
        }
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}