package com.vapeart.domain.usecases.sign_in_usecases

import com.vapeart.domain.repositories.SignInRepository

class SignOutUseCase(private val signInRepo: SignInRepository) {

    operator fun invoke(){
        signInRepo.signOut()
    }
}