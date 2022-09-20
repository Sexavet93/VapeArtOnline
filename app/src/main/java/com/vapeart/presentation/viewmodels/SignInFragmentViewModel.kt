package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vapeart.R
import com.vapeart.data.repositories.SignInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignInFragmentViewModel @Inject constructor(private var firebaseAuthRepo: SignInRepository) : BaseViewModel() {

    fun signIn(email: String, password: String): Boolean {
        return if(isEmailValidate(email) && isPasswordValidate(password)){
            firebaseAuthRepo.signIn(email, password) {
                _isSuccess.value = it
            }
            true
        } else false
    }

}