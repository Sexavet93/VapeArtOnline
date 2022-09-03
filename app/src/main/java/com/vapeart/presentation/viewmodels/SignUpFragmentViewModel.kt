package com.vapeart.presentation.viewmodels

import com.vapeart.data.repositories.SignUpRepository

class SignUpFragmentViewModel : BaseViewModel() {

    private val signUpRepo: SignUpRepository = SignUpRepository.getInstance()

    fun signUp(email: String, password: String, confirmPassword: String): Boolean {
        return if(isEmailValidate(email) && isPasswordValidate(password) && isConfirmPasswordValidate(password,confirmPassword)){
            signUpRepo.createUser(email, password) {
                _isSuccess.value = it
            }
            true
        } else false
    }

    fun isConfirmPasswordValidate(password: String, confirmPassword: String): Boolean{
        return password == confirmPassword
    }
}