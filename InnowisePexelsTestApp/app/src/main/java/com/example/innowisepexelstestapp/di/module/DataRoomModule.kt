package com.example.innowisepexelstestapp.di.module

import android.content.Context
import androidx.room.Room
import com.example.innowisepexelstestapp.repository.room.PhotoPexelsDao
import com.example.innowisepexelstestapp.repository.FavoritePhotoManager
import com.example.innowisepexelstestapp.repository.room.FavoritePhotoDataBase
import com.example.innowisepexelstestapp.repository.room.FavoritePhotoManagerImpl

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataRoomModule {

    @Singleton
    @Provides
    fun provideFavoritePhotoManager(photoPexelsDao: PhotoPexelsDao): FavoritePhotoManager {
        return FavoritePhotoManagerImpl(photoPexelsDao)
    }

    @Singleton
    @Provides
    fun providePhotoPexelsDao(context: Context): PhotoPexelsDao {
        return Room.databaseBuilder(
            context, FavoritePhotoDataBase::class.java, "roomDBCategories"
        ).allowMainThreadQueries().build().photoPexelsDao()
    }

}