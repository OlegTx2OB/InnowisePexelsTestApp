package com.example.innowisepexelstestapp.repository

import io.reactivex.Single

interface ImageDownloader {
    fun saveToGalleryImage(imageUrl: String): Single<Boolean>
}