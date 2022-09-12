package com.vapeart.presentation.utils

import androidx.navigation.NavDirections

interface Navigator {

    fun navigate(direction: NavDirections)

    fun navigateUp()

    fun viewVisibility(isVisible: Boolean)

}