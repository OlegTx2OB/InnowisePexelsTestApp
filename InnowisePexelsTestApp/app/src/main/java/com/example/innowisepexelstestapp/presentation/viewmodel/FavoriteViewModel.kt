package com.example.innowisepexelstestapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.innowisepexelstestapp.presentation.navigation.Screens
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val mRouter: Router) : ViewModel() {
    fun navigateToHome() {
        mRouter.newRootScreen(Screens.homeFragment())
    }
}