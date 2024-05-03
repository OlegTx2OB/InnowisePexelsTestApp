package com.example.innowisepexelstestapp.di.module

import android.content.Context
import com.example.innowisepexelstestapp.repository.ImageDownloader
import com.example.innowisepexelstestapp.repository.ImageDownloaderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataDownloadModule {

    @Singleton
    @Provides
    fun provideImageDownloaderImpl(context: Context): ImageDownloader {
        return ImageDownloaderImpl(context)
    }

}