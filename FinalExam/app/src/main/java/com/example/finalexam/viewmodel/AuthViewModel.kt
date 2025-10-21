package com.example.finalexam.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalexam.MyApplication
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application as MyApplication
    private val prefs = app.preferencesManager

    val isLoggedIn: StateFlow<Boolean> = prefs.isLoggedIn
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun login(username: String) {
        viewModelScope.launch {
            prefs.setUsername(username)
            prefs.setLoggedIn(true)
        }
    }

    fun logout() {
        viewModelScope.launch {
            prefs.setLoggedIn(false)
        }
    }
}

