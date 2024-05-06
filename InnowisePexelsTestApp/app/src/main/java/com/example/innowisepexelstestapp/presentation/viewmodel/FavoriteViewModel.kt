package com.example.innowisepexelstestapp.presentation.viewmodel

import android.view.View
import android.view.animation.AlphaAnimation
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
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
        val photos = mFavoritePhotoManager.getAllFavoritePhoto()
        mAdapter.addPhotoListForFavoriteScreen(photos)
        showRvAlphaAnimation(favoriteRv)

        if(photos.isNotEmpty()) {
            tvNoFavorites.visibility = View.GONE
            tvExplore.visibility = View.GONE
        }
    }

    private fun showRvAlphaAnimation(favoriteRv: RecyclerView) {
        val fadeInAnimation = AlphaAnimation(0f, 1f)
        fadeInAnimation.duration = 300
        favoriteRv.startAnimation(fadeInAnimation)
    }
}