package com.vapeart.domain.usecases.sign_in_usecases

import com.vapeart.domain.repositories.SignInRepository

class IsUserRegisteredUseCase(private val signInRepo: SignInRepository) {

    operator fun invoke(): Boolean{
        return signInRepo.isUserRegistered()
    }
}