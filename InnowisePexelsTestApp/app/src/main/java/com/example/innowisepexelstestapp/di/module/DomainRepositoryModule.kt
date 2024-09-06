package com.example.innowisepexelstestapp.di.module

import android.content.Context
import com.example.innowisepexelstestapp.repository.DownloadFilesManager
import com.example.innowisepexelstestapp.repository.FavoritePhotoManager
import com.example.innowisepexelstestapp.repository.NetworkManager
import com.example.innowisepexelstestapp.repository.SignInSignUpManager
import com.example.innowisepexelstestapp.repository.downloadmanager.DownloadFilesManagerImpl
import com.example.innowisepexelstestapp.repository.firebase.FirebaseFavoritePhotoManagerImpl
import com.example.innowisepexelstestapp.repository.firebase.SignInSignUpManagerImpl
import com.example.innowisepexelstestapp.repository.pexelsapi.NetworkManagerImpl
import com.example.innowisepexelstestapp.repository.pexelsapi.PexelsNetworkClient
import com.example.innowisepexelstestapp.repository.room.RoomFavoritePhotoManagerImpl
import com.example.innowisepexelstestapp.repository.room.PhotoPexelsDao
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

const val ROOM_DB = "room"
const val FIREBASE_DB = "firebase"

@Module
class DomainRepositoryModule {

    @Singleton
    @Provides
    fun provideNetworkManagerImpl(networkClient: PexelsNetworkClient): NetworkManager {
        return NetworkManagerImpl(networkClient)
    }

    @Singleton
    @Provides
    @Named(ROOM_DB)
    fun provideRoomFavoritePhotoManager(photoPexelsDao: PhotoPexelsDao): FavoritePhotoManager {
        return RoomFavoritePhotoManagerImpl(photoPexelsDao)
    }

    @Singleton
    @Provides
    @Named(FIREBASE_DB)
    fun provideFirebaseFavoritePhotoManager(): FavoritePhotoManager {
        return FirebaseFavoritePhotoManagerImpl()
    }

    @Singleton
    @Provides
    fun provideDownloadFilesManager(mAppContext: Context): DownloadFilesManager {
        return DownloadFilesManagerImpl(mAppContext)
    }

    @Singleton
    @Provides
    fun provideSignInSignUpManager(): SignInSignUpManager {
        return SignInSignUpManagerImpl()
    }

}