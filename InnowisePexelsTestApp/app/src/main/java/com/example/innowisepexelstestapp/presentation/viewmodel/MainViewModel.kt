package com.example.innowisepexelstestapp.presentation.viewmodel

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.innowisepexelstestapp.presentation.navigation.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mRouter: Router) : ViewModel() {

    private val _ldBottomNavVisibility = MutableLiveData<Int>()
    val ldBottomNavVisibility: LiveData<Int> = _ldBottomNavVisibility
    @SuppressLint("CheckResult")
    fun delayedToHomeScreen() {
        _ldBottomNavVisibility.value = View.GONE
        Observable.timer(1700, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _ldBottomNavVisibility.value = View.VISIBLE
                toHomeScreen()
            }
    }
    fun toHomeScreen() {
        mRouter.newRootScreen(Screens.homeFragment())
    }

    fun toFavoriteScreen() {
        mRouter.newRootScreen(Screens.favoriteFragment())
    }
}