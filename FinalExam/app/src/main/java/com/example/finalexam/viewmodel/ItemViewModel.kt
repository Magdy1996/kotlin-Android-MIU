package com.example.finalexam.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalexam.MyApplication
import com.example.finalexam.data.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application as MyApplication
    private val repo = app.repository

    val items: StateFlow<List<Item>> = repo.getAllItems()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val _selectedItem = MutableStateFlow<Item?>(null)
    val selectedItem: StateFlow<Item?> = _selectedItem

    fun loadItem(id: Long) {
        viewModelScope.launch {
            val it = repo.getById(id)
            _selectedItem.value = it
        }
    }

    fun insert(item: Item, onComplete: (Long) -> Unit = {}) {
        viewModelScope.launch {
            val id = repo.insert(item)
            onComplete(id)
        }
    }

    fun update(item: Item) {
        viewModelScope.launch {
            repo.update(item)
        }
    }

    fun delete(item: Item) {
        viewModelScope.launch {
            repo.delete(item)
        }
    }
}
