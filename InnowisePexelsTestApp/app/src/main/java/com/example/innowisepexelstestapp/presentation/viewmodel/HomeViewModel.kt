package com.example.innowisepexelstestapp.presentation.viewmodel

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.presentation.navigation.Screens
import com.example.innowisepexelstestapp.presentation.view.DetailsFragment
import com.github.terrakok.cicerone.Router
import com.makeramen.roundedimageview.RoundedImageView
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val mRouter: Router) : ViewModel() {
    fun onClickPhoto(photoPexels: PhotoPexels) {
        mRouter.navigateTo(Screens.detailsFragment(photoPexels))
    }

    fun navigateToFavorite() {
        mRouter.navigateTo(Screens.favoriteFragment())
    }

    fun onTvTryAgain() {
        //todo
        TODO("Not yet implemented")
    }
}