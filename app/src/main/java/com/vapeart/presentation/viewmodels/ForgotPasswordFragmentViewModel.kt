package com.vapeart.presentation.viewmodels

import com.vapeart.domain.repositories.SignInRepository
import com.vapeart.domain.usecases.sign_in_usecases.SendPasswordResetEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordFragmentViewModel @Inject constructor(private var sigInRepo: SignInRepository): BaseViewModel() {

    private val sendPasswordResetEmailUseCase: SendPasswordResetEmailUseCase = SendPasswordResetEmailUseCase(sigInRepo)

    fun sendPasswordResetEmail(email: String): Boolean {
        return if(isEmailValidate(email)){
            sendPasswordResetEmailUseCase.sendPasswordResetEmail(email) { isSuccessful, message ->
                _isSuccess.value = isSuccessful
                _exceptionMessage.value = message
            }
            true
        } else false
    }
}