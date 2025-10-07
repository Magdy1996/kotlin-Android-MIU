package com.example.myapplication.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Represents a single image item in the explorer.
 *
 * @property id A unique identifier for the item.
 * @property titleResId The string resource ID for the title of the image.
 * @property drawableResId The drawable resource ID for the image.
 */
data class ImageItem(
    val id: Int,
    @StringRes val titleResId: Int,
    @DrawableRes val drawableResId: Int
)