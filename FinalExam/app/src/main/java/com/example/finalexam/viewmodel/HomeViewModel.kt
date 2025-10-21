package com.example.finalexam.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalexam.MyApplication
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application as MyApplication
    private val prefs = app.preferencesManager

    val lastJoke: StateFlow<String?> = prefs.lastJoke
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)
}

