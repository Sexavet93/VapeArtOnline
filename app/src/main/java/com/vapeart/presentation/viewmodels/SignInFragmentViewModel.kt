package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vapeart.data.repositories.SignInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInFragmentViewModel @Inject constructor(private var firebaseAuthRepo: SignInRepository) : BaseViewModel() {

    fun signIn(email: String, password: String): Boolean {
        return if(isEmailValidate(email) && isPasswordValidate(password)){
            firebaseAuthRepo.signIn(email, password) { isSuccessful, message ->
                _isSuccess.value = isSuccessful
                _exceptionMessage.value = message
            }
            true
        } else false
    }

}