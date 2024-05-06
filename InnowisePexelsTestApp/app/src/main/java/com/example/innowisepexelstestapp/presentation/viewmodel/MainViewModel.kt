package com.example.innowisepexelstestapp.presentation.viewmodel

import android.annotation.SuppressLint
import android.view.View
import android.view.animation.Animation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.util.ResourceProvider
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mResourceProvider: ResourceProvider) : ViewModel() {

    private val _ldLogoVisibility: MutableLiveData<Int> = MutableLiveData()
    private val _ldLogoStartAnim: MutableLiveData<Animation> = MutableLiveData()
    private val _ldLogoBackgroundVisibility: MutableLiveData<Int> = MutableLiveData()

    val ldLogoVisibility: LiveData<Int> = _ldLogoVisibility
    val ldLogoStartAnim: LiveData<Animation> = _ldLogoStartAnim
    val ldLogoBackgroundVisibility: LiveData<Int> = _ldLogoBackgroundVisibility

    @SuppressLint("CheckResult")
    fun delayedHideSplashScreen() {
        io.reactivex.Observable.timer(1200, java.util.concurrent.TimeUnit.MILLISECONDS)
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe {
                _ldLogoVisibility.value = View.GONE
                _ldLogoBackgroundVisibility.value = View.GONE
            }
    }

    fun startSplashScreenAnim() {
        val animation = mResourceProvider.getAnim(R.anim.fall_animation)
        _ldLogoStartAnim.value = animation
    }

}