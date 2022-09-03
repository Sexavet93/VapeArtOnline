package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vapeart.R
import com.vapeart.data.repositories.SignInRepository
import java.util.regex.Pattern

class SignInFragmentViewModel : BaseViewModel() {


    private val firebaseAuthRepo: SignInRepository = SignInRepository.getInstance()

    fun signIn(email: String, password: String): Boolean {
        return if(isEmailValidate(email) && isPasswordValidate(password)){
            firebaseAuthRepo.signIn(email, password) {
                _isSuccess.value = it
            }
            true
        } else false
    }

}