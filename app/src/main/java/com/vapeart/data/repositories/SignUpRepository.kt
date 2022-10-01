package com.vapeart.data.repositories

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpRepository @Inject constructor(private var firebaseAuth: FirebaseAuth){

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun createUser(email: String, password: String, callback: (Boolean,String) -> Unit){
        coroutineScope.launch {
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    task.exception?.message?.let { message ->
                        callback.invoke(task.isSuccessful,message)
                    } ?: callback.invoke(task.isSuccessful, "")
                }
        }
    }
}