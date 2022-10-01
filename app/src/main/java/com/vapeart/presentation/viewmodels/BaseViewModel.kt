package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

abstract class BaseViewModel (): ViewModel() {

    protected val _exceptionMessage: MutableLiveData<String> = MutableLiveData()
    val exceptionMessage: LiveData<String>
        get() = _exceptionMessage

    protected val _isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess
    private val pattern: String = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}"

    fun isEmailValidate(email: String): Boolean{
        return Pattern.matches(pattern, email)
    }

    fun isPasswordValidate(password: String): Boolean{
        return password.length >= 7
    }
}