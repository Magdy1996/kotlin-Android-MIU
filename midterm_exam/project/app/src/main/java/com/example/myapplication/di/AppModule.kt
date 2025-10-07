package com.example.myapplication.di

import com.example.myapplication.data.repository.IImageRepository
import com.example.myapplication.data.repository.StaticImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindImageRepository(
        imageRepository: StaticImageRepository
    ): IImageRepository
}