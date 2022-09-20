package com.vapeart.presentation.utils


import androidx.appcompat.widget.SearchView

abstract class OnQueryTextListenerImpl: SearchView.OnQueryTextListener {

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}