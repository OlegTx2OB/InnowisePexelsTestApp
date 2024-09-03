package com.example.innowisepexelstestapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val mRouter: Router
) : ViewModel() {

    fun onSignInBtn() {
        mRouter.exit()
    }
}