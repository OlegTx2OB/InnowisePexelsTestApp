package com.example.innowisepexelstestapp.di.module

import com.example.innowisepexelstestapp.repository.DownloadFilesManager
import com.example.innowisepexelstestapp.repository.FavoritePhotoManager
import com.example.innowisepexelstestapp.repository.SignInSignUpManager
import com.example.innowisepexelstestapp.usecase.DeleteImageFromRoomUseCase
import com.example.innowisepexelstestapp.usecase.DownloadImageUseCase
import com.example.innowisepexelstestapp.usecase.SaveImageIntoRoomUseCase
import com.example.innowisepexelstestapp.usecase.SignInUseCase
import com.example.innowisepexelstestapp.usecase.SignUpUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainUseCaseModule {

    @Provides
    fun provideSaveImageIntoRoomUseCase(favoritePhotoManager: FavoritePhotoManager): SaveImageIntoRoomUseCase {
        return SaveImageIntoRoomUseCase(favoritePhotoManager)
    }

    @Provides
    fun provideDeleteImageFromRoomUseCase(favoritePhotoManager: FavoritePhotoManager): DeleteImageFromRoomUseCase {
        return DeleteImageFromRoomUseCase(favoritePhotoManager)
    }

    @Provides
    fun provideDownloadImageUseCase(downloadFilesManager: DownloadFilesManager): DownloadImageUseCase {
        return DownloadImageUseCase(downloadFilesManager)
    }

    @Provides
    fun provideSignUpUseCase(signInSignUpManager: SignInSignUpManager): SignUpUseCase {
        return SignUpUseCase(signInSignUpManager)
    }

    @Provides
    fun provideSignInUseCase(signInSignUpManager: SignInSignUpManager): SignInUseCase {
        return SignInUseCase(signInSignUpManager)
    }

}