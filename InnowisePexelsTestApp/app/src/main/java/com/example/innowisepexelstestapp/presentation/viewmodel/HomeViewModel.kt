package com.example.innowisepexelstestapp.presentation.viewmodel

import android.annotation.SuppressLint
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.innowisepexelstestapp.databinding.FragmentHomeBinding
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.presentation.navigation.Screens
import com.example.innowisepexelstestapp.presentation.rv.RvPhotoAdapter
import com.example.innowisepexelstestapp.presentation.view.DetailsFragment
import com.example.innowisepexelstestapp.repository.NetworkManager
import com.github.terrakok.cicerone.Router
import com.makeramen.roundedimageview.RoundedImageView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val mRouter: Router) : ViewModel() {
    fun onClickPhoto(photoPexels: PhotoPexels) {
        mRouter.navigateTo(Screens.detailsFragment(photoPexels))
    }

    fun navigateToFavorite() {
        mRouter.navigateTo(Screens.favoriteFragment())
    }

    @SuppressLint("CheckResult")
    fun setPhotos(binding : FragmentHomeBinding,
                          mNetworkManager: NetworkManager,
                          mAdapter: RvPhotoAdapter) = with(binding) {
        mNetworkManager.getCuratedPhotos()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ photos ->
                ivNonetwork.visibility = View.GONE
                tvTryAgain.visibility = View.GONE
                mAdapter.addPhotoPexelsList(photos)
            }, {
                ivNonetwork.visibility = View.VISIBLE
                tvTryAgain.visibility = View.VISIBLE
            })
        showRvAlphaAnimation(homeRv)
    }

    private fun showRvAlphaAnimation(homeRv: RecyclerView) {
        val fadeInAnimation = AlphaAnimation(0f, 1f)
        fadeInAnimation.duration = 300
        homeRv.startAnimation(fadeInAnimation)
    }
}