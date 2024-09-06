package com.example.innowisepexelstestapp.di.module

import com.example.innowisepexelstestapp.repository.DownloadFilesManager
import com.example.innowisepexelstestapp.repository.FavoritePhotoManager
import com.example.innowisepexelstestapp.repository.SignInSignUpManager
import com.example.innowisepexelstestapp.usecase.DeleteImageFromBdUseCase
import com.example.innowisepexelstestapp.usecase.DownloadImageUseCase
import com.example.innowisepexelstestapp.usecase.GetImagesFromBdUseCase
import com.example.innowisepexelstestapp.usecase.SaveImageIntoBdUseCase
import com.example.innowisepexelstestapp.usecase.SignInUseCase
import com.example.innowisepexelstestapp.usecase.SignUpUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class DomainUseCaseModule {

    @Provides
    fun provideSaveImageIntoBdUseCase(@Named(FIREBASE_DB) favoritePhotoManager: FavoritePhotoManager): SaveImageIntoBdUseCase {
        return SaveImageIntoBdUseCase(favoritePhotoManager)
    }

    @Provides
    fun provideDeleteImageFromBdUseCase(@Named(FIREBASE_DB) favoritePhotoManager: FavoritePhotoManager): DeleteImageFromBdUseCase {
        return DeleteImageFromBdUseCase(favoritePhotoManager)
    }

    @Provides
    fun provideGetImagesFromBdUseCase(@Named(FIREBASE_DB) favoritePhotoManager: FavoritePhotoManager): GetImagesFromBdUseCase {
        return GetImagesFromBdUseCase(favoritePhotoManager)
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