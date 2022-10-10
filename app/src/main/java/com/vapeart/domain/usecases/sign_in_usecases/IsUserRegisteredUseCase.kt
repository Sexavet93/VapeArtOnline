package com.vapeart.domain.usecases.sign_in_usecases

import com.vapeart.domain.repositories.SignInRepository

class IsUserRegisteredUseCase(private val signInRepo: SignInRepository) {

    fun isUserRegistered(): Boolean{
        return signInRepo.isUserRegistered()
    }
}