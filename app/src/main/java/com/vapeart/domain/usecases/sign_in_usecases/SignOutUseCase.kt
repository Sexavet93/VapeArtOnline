package com.vapeart.domain.usecases.sign_in_usecases

import com.vapeart.domain.repositories.SignInRepository

class SignOutUseCase(private val signInRepo: SignInRepository) {

    fun signOut(){
        signInRepo.signOut()
    }
}