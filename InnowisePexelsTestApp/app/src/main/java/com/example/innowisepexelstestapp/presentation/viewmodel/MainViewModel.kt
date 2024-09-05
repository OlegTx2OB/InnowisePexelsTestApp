package com.example.innowisepexelstestapp.presentation.viewmodel

import android.annotation.SuppressLint
import android.view.View
import android.view.animation.Animation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.presentation.navigation.Screens
import com.example.innowisepexelstestapp.repository.SignInSignUpManager
import com.example.innowisepexelstestapp.util.ResourceProvider
import com.github.terrakok.cicerone.androidx.FragmentScreen
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mResourceProvider: ResourceProvider,
    private val signInSignUpManager: SignInSignUpManager) : ViewModel() {

    private val _ldLogoVisibility: MutableLiveData<Int> = MutableLiveData()
    private val _ldLogoStartAnim: MutableLiveData<Animation> = MutableLiveData()
    private val _ldLogoBackgroundVisibility: MutableLiveData<Int> = MutableLiveData()
    private val _ldSetStartFragment: MutableLiveData<FragmentScreen> = MutableLiveData()

    val ldLogoVisibility: LiveData<Int> = _ldLogoVisibility
    val ldLogoStartAnim: LiveData<Animation> = _ldLogoStartAnim
    val ldLogoBackgroundVisibility: LiveData<Int> = _ldLogoBackgroundVisibility
    val ldSetStartFragment: LiveData<FragmentScreen> = _ldSetStartFragment

    init {
        startSplashScreenAnim()
        delayedHideSplashScreen()
        redirectIfLoggedIn()
    }

    @SuppressLint("CheckResult")
    private fun redirectIfLoggedIn() {
        signInSignUpManager.isUserSignedUp()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isUserSignedUp ->
                _ldSetStartFragment.value = if (isUserSignedUp) {
                    Screens.homeFragment()
                } else {
                    Screens.signInFragment()
                }
            }
    }

    @SuppressLint("CheckResult")
    private fun delayedHideSplashScreen() {
        Observable.timer(1200, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _ldLogoVisibility.value = View.GONE
                _ldLogoBackgroundVisibility.value = View.GONE
            }
    }

    private fun startSplashScreenAnim() {
        val animation = mResourceProvider.getAnim(R.anim.fall_animation)
        _ldLogoStartAnim.value = animation
    }

}