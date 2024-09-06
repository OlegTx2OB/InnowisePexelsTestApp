package com.example.innowisepexelstestapp.usecase

import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.repository.FavoritePhotoManager

class GetImagesFromBdUseCase(private val mFavoritePhotoManager: FavoritePhotoManager) {
    fun execute(): List<PhotoPexels> {
        return mFavoritePhotoManager.getAllFavoritePhoto()
    }
}