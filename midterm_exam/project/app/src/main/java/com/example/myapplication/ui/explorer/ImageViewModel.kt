package com.example.myapplication.ui.explorer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.IImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel for the Image Explorer screen.
 *
 * This ViewModel is responsible for holding and managing the UI-related data
 * in a lifecycle-conscious way. It communicates with the repository to fetch data.
 *
 * @param repository The repository that provides image data.
 */
@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: IImageRepository
) : ViewModel() {

    private val initialImage = repository.getInitial()

    // Private mutable state flow that can be updated internally.
    private val _uiState = MutableStateFlow(UiState(initialImage.drawableResId, initialImage.titleResId))

    /**
     * The public, read-only state flow that the UI can observe for changes.
     */
    val uiState: StateFlow<UiState> = _uiState

    /**
     * Fetches the next image from the repository and updates the UI state.
     */
    fun getNextImage() {
        viewModelScope.launch {
            val nextImage = repository.getNext()
            _uiState.value = UiState(nextImage.drawableResId, nextImage.titleResId)
        }
    }
}