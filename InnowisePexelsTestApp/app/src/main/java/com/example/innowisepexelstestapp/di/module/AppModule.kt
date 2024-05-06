package com.example.innowisepexelstestapp.di.module

import android.content.Context
import com.example.innowisepexelstestapp.util.ResourceProvider
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideResourceProvider(mAppContext: Context): ResourceProvider {
        return ResourceProvider(mAppContext)
    }
}