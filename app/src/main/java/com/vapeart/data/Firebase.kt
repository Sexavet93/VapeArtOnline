package com.vapeart.data

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Firebase private constructor(context: Context) {

    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseCloud: FirebaseFirestore = Firebase.firestore

    companion object{
        private var INSTANCE: com.vapeart.data.Firebase? = null

        fun initializeFirebase(context: Context){
            if (INSTANCE == null) INSTANCE = Firebase(context)
        }

        fun getInstance(): com.vapeart.data.Firebase {
            INSTANCE?.let { return it } ?: throw RuntimeException("INSTANCE is null")
        }
    }
}