package com.example.innowisepexelstestapp.di.module

import com.example.innowisepexelstestapp.repository.NetworkManager
import com.example.innowisepexelstestapp.repository.NetworkManagerImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class DataNetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Singleton
    @Provides
    fun provideNetworkManager(okHttpClient: OkHttpClient): NetworkManager {
        return NetworkManagerImpl(okHttpClient)
    }

}