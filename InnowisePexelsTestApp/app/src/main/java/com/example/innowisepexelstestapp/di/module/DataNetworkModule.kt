package com.example.innowisepexelstestapp.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class DataNetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

}