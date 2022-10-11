package com.vapeart.domain.usecases.sign_in_usecases

import com.vapeart.domain.repositories.SignInRepository

class SignInUseCase(private val signInRepo: SignInRepository) {

    operator fun invoke(email: String ,password: String, callback: (Boolean, String) -> Unit){
        signInRepo.signIn(email, password, callback)
    }
}