package com.vapeart.presentation.viewmodels

import com.vapeart.domain.repositories.SignUpRepository
import com.vapeart.domain.usecases.sign_up_usecases.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpFragmentViewModel @Inject constructor(private var signUpRepo: SignUpRepository) : BaseViewModel() {

    private val signUpUseCase: SignUpUseCase = SignUpUseCase(signUpRepo)

    fun signUp(email: String, password: String, confirmPassword: String): Boolean {
        return if(isEmailValidate(email) && isPasswordValidate(password) && isConfirmPasswordValidate(password,confirmPassword)){
            signUpUseCase.createUser(email, password){ isSuccessful, message ->
                _isSuccess.value = isSuccessful
                _exceptionMessage.value = message
            }
            true
        } else false
    }

    fun isConfirmPasswordValidate(password: String, confirmPassword: String): Boolean{
        return password == confirmPassword
    }
}