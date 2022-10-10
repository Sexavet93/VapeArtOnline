package com.vapeart.domain.repositories

interface SignUpRepository {

    fun createUser(email: String, password: String, callback: (Boolean,String) -> Unit)
}