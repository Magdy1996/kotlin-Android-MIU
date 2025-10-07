package com.example.myapplication.data.repository

import com.example.myapplication.data.model.ImageItem

/**
 * Defines the contract for a repository that provides image data.
 * This interface abstracts the data source from the ViewModel.
 */
interface IImageRepository {
    /**
     * Returns the first image item to be displayed.
     */
    fun getInitial(): ImageItem

    /**
     * Returns the next image item in a circular manner.
     */
    fun getNext(): ImageItem
}
