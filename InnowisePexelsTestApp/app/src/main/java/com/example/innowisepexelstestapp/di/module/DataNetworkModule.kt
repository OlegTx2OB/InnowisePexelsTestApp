package com.example.innowisepexelstestapp.di.module

import com.example.innowisepexelstestapp.repository.NetworkManager
import com.example.innowisepexelstestapp.repository.NetworkManagerImpl
import com.example.innowisepexelstestapp.repository.network.api.PexelsCollectionsNetworkClient
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
    fun providePexelsCollectionsNetworkClient(okHttpClient: OkHttpClient): PexelsCollectionsNetworkClient {
        return PexelsCollectionsNetworkClient(okHttpClient)
    }

    @Singleton
    @Provides
    fun provideNetworkManagerImpl(networkClient: PexelsCollectionsNetworkClient): NetworkManager {
        return NetworkManagerImpl(networkClient)
    }

}