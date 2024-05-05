package com.example.innowisepexelstestapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.innowisepexelstestapp.databinding.FragmentFavoriteBinding
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.presentation.navigation.Screens
import com.example.innowisepexelstestapp.presentation.rv.RvPhotoAdapter
import com.example.innowisepexelstestapp.repository.FavoritePhotoManager
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val mRouter: Router) : ViewModel() {

    @Inject
    lateinit var mFavoritePhotoManager: FavoritePhotoManager

    fun onClickPhoto(photoPexels: PhotoPexels) {
        mRouter.navigateTo(Screens.detailsFragment(photoPexels, isItLikedPhoto = true))
    }

    fun navigateToHome() {
        mRouter.exit()
    }

    fun setPhotos(
        binding: FragmentFavoriteBinding,
        mAdapter: RvPhotoAdapter
    ) = with(binding) {
        mAdapter.addPhotoPexelsList(mFavoritePhotoManager.getAllFavoritePhoto())
    }
}