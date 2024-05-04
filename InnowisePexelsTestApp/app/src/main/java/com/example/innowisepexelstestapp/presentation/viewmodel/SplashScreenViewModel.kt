package com.example.innowisepexelstestapp.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.innowisepexelstestapp.presentation.navigation.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(private val mRouter: Router) : ViewModel() {
    @SuppressLint("CheckResult")
    fun delayedToHome() {
        Observable.timer(1200, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                toHomeScreen()
            }
    }

    private fun toHomeScreen() {
        mRouter.newRootScreen(Screens.homeFragment())
    }
}