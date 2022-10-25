package com.vapeart.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vapeart.domain.models.SelectedItem
import com.vapeart.domain.repositories.RoomRepository
import com.vapeart.domain.repositories.SignInRepository
import com.vapeart.domain.usecases.local_db_usecases.selected_items_usecases.GetSelectedItemsUseCase
import com.vapeart.domain.usecases.sign_in_usecases.IsUserRegisteredUseCase
import com.vapeart.domain.usecases.sign_in_usecases.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    roomRepo: RoomRepository,
    signInRepo: SignInRepository
) : ViewModel() {

    private val getSelectedItemsUseCase: GetSelectedItemsUseCase = GetSelectedItemsUseCase(roomRepo)
    private val signOutUseCase: SignOutUseCase = SignOutUseCase(signInRepo)
    private val isUserRegisteredUseCase: IsUserRegisteredUseCase = IsUserRegisteredUseCase(signInRepo)
    val selectedItemLiveData: LiveData<List<SelectedItem>> = getSelectedItemsUseCase()

    fun isUserRegistered(): Boolean {
        return isUserRegisteredUseCase()
    }

    fun signOut() {
        signOutUseCase()
    }
}