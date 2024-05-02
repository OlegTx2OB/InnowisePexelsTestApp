package com.example.innowisepexelstestapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val mRouter: Router) : ViewModel() {
    fun onDownloadBtn() {
        TODO("Not yet implemented")
    }

    fun onBackBtn() {
        mRouter.exit()
    }

    fun onFavoriteBtn() {
        TODO("Not yet implemented")
    }
}