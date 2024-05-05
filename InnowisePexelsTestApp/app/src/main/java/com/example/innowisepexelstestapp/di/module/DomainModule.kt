package com.example.innowisepexelstestapp.di.module

import com.example.innowisepexelstestapp.repository.FavoritePhotoManager
import com.example.innowisepexelstestapp.usecase.DeleteImageFromRoomUseCase
import com.example.innowisepexelstestapp.usecase.SaveImageIntoRoomUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideSaveImageIntoRoomUseCase(favoritePhotoManager: FavoritePhotoManager): SaveImageIntoRoomUseCase {
        return SaveImageIntoRoomUseCase(favoritePhotoManager)
    }

    @Provides
    fun provideDeleteImageFromRoomUseCase(favoritePhotoManager: FavoritePhotoManager): DeleteImageFromRoomUseCase {
        return DeleteImageFromRoomUseCase(favoritePhotoManager)
    }

}