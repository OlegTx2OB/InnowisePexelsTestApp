package com.example.innowisepexelstestapp.usecase

import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.repository.FavoritePhotoManager

class SaveImageIntoRoomUseCase(private val mFavoritePhotoManager: FavoritePhotoManager) {
    fun execute(photoPexels: PhotoPexels) {
        mFavoritePhotoManager.insertFavoritePhoto(photoPexels)
    }
}