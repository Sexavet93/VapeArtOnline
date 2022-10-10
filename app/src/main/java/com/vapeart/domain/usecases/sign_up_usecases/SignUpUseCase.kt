package com.vapeart.domain.usecases.sign_up_usecases

import com.vapeart.domain.repositories.SignUpRepository

class SignUpUseCase(private val signUpRepo: SignUpRepository) {

    fun createUser(email: String, password: String, callback: (Boolean,String) -> Unit){
        signUpRepo.createUser(email,password,callback)
    }
}