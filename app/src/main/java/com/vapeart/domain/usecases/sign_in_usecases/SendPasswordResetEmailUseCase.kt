package com.vapeart.domain.usecases.sign_in_usecases

import com.vapeart.domain.repositories.SignInRepository

class SendPasswordResetEmailUseCase(private val signInRepo: SignInRepository) {

    fun sendPasswordResetEmail(email:String, callback: (Boolean, String) -> Unit){
        signInRepo.sendPasswordResetEmail(email, callback)
    }
}