package com.example.innowisepexelstestapp.repository

import com.example.innowisepexelstestapp.model.PhotoPexels

interface NetworkManager {
    fun getCuratedPhotos(): List<PhotoPexels>
}