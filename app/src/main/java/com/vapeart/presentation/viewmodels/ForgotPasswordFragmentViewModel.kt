package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vapeart.data.repositories.SignInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordFragmentViewModel @Inject constructor(private var firebaseAuthRepo: SignInRepository): BaseViewModel() {

    fun sendPasswordResetEmail(email: String): Boolean {
        return if(isEmailValidate(email)){
            firebaseAuthRepo.sendPasswordResetEmail(email) { isSuccessful, message ->
                _isSuccess.value = isSuccessful
                _exceptionMessage.value = message
            }
            true
        } else false
    }
}