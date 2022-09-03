package com.vapeart.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.vapeart.data.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpRepository {

    private val firebaseAuth: FirebaseAuth = Firebase.getInstance().firebaseAuth
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun createUser(email: String, password: String, callback: (Boolean) -> Unit){
        coroutineScope.launch {
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener{ task ->
                    callback.invoke(task.isSuccessful)
                }
        }
    }

    companion object{
        private var INSTANCE: SignUpRepository? = null

        fun getInstance(): SignUpRepository{
            if(INSTANCE == null) INSTANCE = SignUpRepository()
            INSTANCE?.let { return it } ?: throw RuntimeException("INSTANCE is null")
        }
    }
}