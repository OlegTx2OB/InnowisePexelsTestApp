package com.example.innowisepexelstestapp.presentation.viewmodel

import android.annotation.SuppressLint
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.presentation.navigation.Screens
import com.example.innowisepexelstestapp.repository.SignInSignUpManager
import com.example.innowisepexelstestapp.usecase.GetImagesFromBdUseCase
import com.github.terrakok.cicerone.Router
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val mRouter: Router,
    private val mGetImagesFromBdUseCase: GetImagesFromBdUseCase,
    private val mSignInSignUpManager: SignInSignUpManager
) : ViewModel() {

    private val _ldTvNoFavoritesVisibility: MutableLiveData<Int> = MutableLiveData()
    private val _ldTvExploreVisibility: MutableLiveData<Int> = MutableLiveData()
    private val _ldAddPhotoList: MutableLiveData<List<PhotoPexels>> = MutableLiveData()
    private val _ldShowAnim: MutableLiveData<AlphaAnimation> = MutableLiveData()

    val ldTvNoFavoritesVisibility: LiveData<Int> = _ldTvNoFavoritesVisibility
    val ldTvExploreVisibility: LiveData<Int> = _ldTvExploreVisibility
    val ldAddPhotoList: LiveData<List<PhotoPexels>> = _ldAddPhotoList
    val ldShowAnim: LiveData<AlphaAnimation> = _ldShowAnim

    fun onClickPhoto(photoPexels: PhotoPexels) {
        mRouter.navigateTo(Screens.detailsFragment(photoPexels, isItLikedPhoto = true))
    }

    fun navigateToHome() {
        mRouter.exit()
    }

    @SuppressLint("CheckResult")
    fun setPhotos() {
        Single.fromCallable {
            mGetImagesFromBdUseCase.execute()
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ photos ->
                _ldAddPhotoList.value = photos
                showRvAlphaAnimation()

                if (photos.isNotEmpty()) {
                    _ldTvNoFavoritesVisibility.value = View.GONE
                    _ldTvExploreVisibility.value = View.GONE
                } else {
                    _ldTvNoFavoritesVisibility.value = View.VISIBLE
                    _ldTvExploreVisibility.value = View.VISIBLE
                }
            }, { throwable ->
                throwable.printStackTrace()
            })
    }

    private fun showRvAlphaAnimation() {
        val fadeInAnimation = AlphaAnimation(0f, 1f)
        fadeInAnimation.duration = 300
        _ldShowAnim.value = fadeInAnimation
    }

    @SuppressLint("CheckResult")
    fun onLogout() {
        mSignInSignUpManager.logOutUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isLogoutSuccessful ->
                if(isLogoutSuccessful) {
                    mRouter.newRootScreen(Screens.signInFragment())
                }
            }
    }
}