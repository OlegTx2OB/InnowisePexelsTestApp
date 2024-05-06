package com.example.innowisepexelstestapp.presentation.viewmodel

import android.annotation.SuppressLint
import android.text.Editable
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.innowisepexelstestapp.databinding.FragmentHomeBinding
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.presentation.navigation.Screens
import com.example.innowisepexelstestapp.presentation.rv.RvPhotoAdapter
import com.example.innowisepexelstestapp.repository.NetworkManager
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val mRouter: Router,
    private val mNetworkManager: NetworkManager) : ViewModel() {

    private val _ldSearchBarEditTextAction: MutableLiveData<Unit> = MutableLiveData()
    private val _ldSearchBarCloseIconVisibility: MutableLiveData<Int> = MutableLiveData()
    private val _ldAddPhotoList: MutableLiveData<List<PhotoPexels>> = MutableLiveData()
    private val _ldIvNoNetworkVisibility: MutableLiveData<Int> = MutableLiveData()
    private val _ldTvTryAgainVisibility: MutableLiveData<Int> = MutableLiveData()
    private val _ldProgressBarVisibility: MutableLiveData<Int> = MutableLiveData()
    private val _ldShowAnim: MutableLiveData<AlphaAnimation> = MutableLiveData()

    val ldSearchBarEditTextAction: LiveData<Unit> = _ldSearchBarEditTextAction
    val ldSearchBarCloseIconVisibility: LiveData<Int> = _ldSearchBarCloseIconVisibility
    val ldAddPhotoList: LiveData<List<PhotoPexels>> = _ldAddPhotoList
    val ldIvNoNetworkVisibility: LiveData<Int> = _ldIvNoNetworkVisibility
    val ldTvTryAgainVisibility: LiveData<Int> = _ldTvTryAgainVisibility
    val ldProgressBarVisibility: LiveData<Int> = _ldProgressBarVisibility
    val ldShowAnim: LiveData<AlphaAnimation> = _ldShowAnim

    fun onClickPhoto(photoPexels: PhotoPexels) {
        mRouter.navigateTo(Screens.detailsFragment(photoPexels, isItLikedPhoto = false))
    }
    fun onSearchBarCloseIcon() {
        _ldSearchBarEditTextAction.value = Unit
    }

    fun navigateToFavorite() {
        mRouter.navigateTo(Screens.favoriteFragment())
    }

    @SuppressLint("CheckResult")
    fun setPhotos() {
        mNetworkManager.getCuratedPhotos()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ photos ->
                _ldIvNoNetworkVisibility.value = View.GONE
                _ldTvTryAgainVisibility.value = View.GONE
                _ldAddPhotoList.value = photos
                _ldProgressBarVisibility.value = View.INVISIBLE
            }, {
                _ldIvNoNetworkVisibility.value = View.VISIBLE
                _ldTvTryAgainVisibility.value = View.VISIBLE
            })
        showRvAlphaAnimation()
    }

    private fun showRvAlphaAnimation() {
        val fadeInAnimation = AlphaAnimation(0f, 1f)
        fadeInAnimation.duration = 300

        _ldShowAnim.value = fadeInAnimation
    }

    fun doAfterTextChanged(editable: Editable) {
        if (editable.toString().trim().isNotEmpty()) {
            _ldSearchBarCloseIconVisibility.value = View.VISIBLE
        } else {
            _ldSearchBarCloseIconVisibility.value = View.GONE
        }
    }

}