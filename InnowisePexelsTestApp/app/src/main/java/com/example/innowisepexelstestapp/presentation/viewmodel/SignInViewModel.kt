package com.example.innowisepexelstestapp.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.presentation.navigation.Screens
import com.example.innowisepexelstestapp.usecase.SignInState
import com.example.innowisepexelstestapp.usecase.SignInUseCase
import com.example.innowisepexelstestapp.util.ResourceProvider
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val mRouter: Router,
    private val signInUseCase: SignInUseCase,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _ldShowToastText: MutableLiveData<String> = MutableLiveData()
    val ldShowToastText: LiveData<String> = _ldShowToastText

    fun onSignUpBtn() {
        mRouter.navigateTo(Screens.signUpFragment())
    }

    @SuppressLint("CheckResult")
    fun onSignInBtn(email: String, password: String) {
        signInUseCase.execute(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { resultState ->
                when (resultState!!) {
                    SignInState.SIGN_IN_FAILED -> {
                        _ldShowToastText.value = resourceProvider
                            .getStringRes(R.string.failed_to_sign_in_to_the_account)
                    }
                    SignInState.SIGN_IN_SUCCESSFUL -> {
                        mRouter.newRootScreen(Screens.homeFragment())
                    }
                }
            }
    }

}