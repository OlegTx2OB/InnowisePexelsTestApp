package com.example.innowisepexelstestapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.innowisepexelstestapp.repository.ImageDownloader
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val mRouter: Router) : ViewModel() {

    @Inject
    lateinit var imageDownloader: ImageDownloader

    fun onDownloadBtn(imageUrl: String) {
        imageDownloader.saveToGalleryImage(imageUrl)
    }

    fun onBackBtn() {
        mRouter.exit()
    }

    fun onFavoriteBtn() {
        TODO("Not yet implemented")
    }
}