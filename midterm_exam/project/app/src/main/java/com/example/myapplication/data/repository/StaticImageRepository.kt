package com.example.myapplication.data.repository

import com.example.myapplication.R
import com.example.myapplication.data.model.ImageItem
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A concrete implementation of [IImageRepository] that provides a static,
 * in-memory list of image items.
 */
@Singleton
class StaticImageRepository @Inject constructor() : IImageRepository {

    private val images = listOf(
        ImageItem(1, R.string.title_rainbow, R.drawable.rainbow),
        ImageItem(2, R.string.title_friends, R.drawable.friends),
        ImageItem(3, R.string.title_miu_campus, R.drawable.miu_campus),
        ImageItem(4, R.string.title_graduation, R.drawable.graduation)
    )

    private var currentIndex = 0

    override fun getInitial(): ImageItem {
        currentIndex = 0
        return images[currentIndex]
    }

    override fun getNext(): ImageItem {
        currentIndex = (currentIndex + 1) % images.size
        return images[currentIndex]
    }
}