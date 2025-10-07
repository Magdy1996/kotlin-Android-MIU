package com.example.myapplication.ui.explorer

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Represents the immutable state of the Image Explorer UI.
 *
 * @property imageResId The drawable resource ID of the current image to display.
 * @property titleResId The string resource ID of the current title to display.
 */
data class UiState(
    @DrawableRes val imageResId: Int,
    @StringRes val titleResId: Int
)