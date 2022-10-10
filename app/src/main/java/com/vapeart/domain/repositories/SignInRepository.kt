package com.vapeart.domain.repositories

interface SignInRepository {

    fun isUserRegistered(): Boolean

    fun signIn(email: String ,password: String, callback: (Boolean, String) -> Unit)

    fun sendPasswordResetEmail(email:String, callback: (Boolean, String) -> Unit)

    fun signOut()
}