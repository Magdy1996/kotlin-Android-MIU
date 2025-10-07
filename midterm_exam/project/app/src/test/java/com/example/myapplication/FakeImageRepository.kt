package com.example.myapplication

import com.example.myapplication.data.model.ImageItem
import com.example.myapplication.data.repository.IImageRepository

class FakeImageRepository : IImageRepository {

    val images = listOf(
        ImageItem(1, R.string.title_rainbow, R.drawable.rainbow),
        ImageItem(2, R.string.title_friends, R.drawable.friends)
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