package com.example.innowisepexelstestapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.presentation.navigation.Screens
import com.github.terrakok.cicerone.Router
import com.makeramen.roundedimageview.RoundedImageView
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val mRouter: Router) : ViewModel() {
    fun onClickPhoto(view: RoundedImageView, photoPexels: PhotoPexels) {
        mRouter.navigateTo(Screens.detailsFragment())
        TODO("NOT IMPLEMENTED YET")
    }

    fun navigateToFavorite() {
        mRouter.navigateTo(Screens.favoriteFragment())
    }
}