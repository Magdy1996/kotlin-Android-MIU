package com.example.myapplication

import com.example.myapplication.ui.explorer.ImageViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ImageViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: ImageViewModel
    private lateinit var repository: FakeImageRepository

    @Before
    fun setup() {
        repository = FakeImageRepository()
        viewModel = ImageViewModel(repository)
    }

    @Test
    fun `getNextImage updates uiState to next image`() = runTest {
        // Initial state is verified by the setup in the ViewModel
        assertEquals(repository.images[0].drawableResId, viewModel.uiState.value.imageResId)
        assertEquals(repository.images[0].titleResId, viewModel.uiState.value.titleResId)

        // Call getNextImage to advance the state
        viewModel.getNextImage()

        // Verify that the uiState now holds the second image
        assertEquals(repository.images[1].drawableResId, viewModel.uiState.value.imageResId)
        assertEquals(repository.images[1].titleResId, viewModel.uiState.value.titleResId)
    }
}