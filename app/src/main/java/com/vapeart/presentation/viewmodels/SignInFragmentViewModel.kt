package com.vapeart.presentation.viewmodels

import com.vapeart.domain.repositories.SignInRepository
import com.vapeart.domain.usecases.sign_in_usecases.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInFragmentViewModel @Inject constructor(signInRepo: SignInRepository)
    : BaseViewModel() {

    private val signInUseCase: SignInUseCase = SignInUseCase(signInRepo)

    fun signIn(email: String, password: String): Boolean {
        return if(isEmailValidate(email) && isPasswordValidate(password)){
            signInUseCase(email, password) { isSuccessful, message ->
                _isSuccess.value = isSuccessful
                _exceptionMessage.value = message
            }
            true
        } else false
    }

}