package com.example.innowisepexelstestapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.presentation.navigation.Screens
import com.github.terrakok.cicerone.Router
import com.makeramen.roundedimageview.RoundedImageView
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val mRouter: Router) : ViewModel() {

    fun onClickPhoto(photoPexels: PhotoPexels) {
        mRouter.navigateTo(Screens.detailsFragment(photoPexels))
    }
    fun navigateToHome() {
        mRouter.exit()
    }
}