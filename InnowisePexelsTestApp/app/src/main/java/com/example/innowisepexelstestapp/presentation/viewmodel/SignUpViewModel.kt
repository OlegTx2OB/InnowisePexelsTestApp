package com.example.innowisepexelstestapp.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.presentation.navigation.Screens
import com.example.innowisepexelstestapp.usecase.SignUpState
import com.example.innowisepexelstestapp.usecase.SignUpUseCase
import com.example.innowisepexelstestapp.util.ResourceProvider
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val mRouter: Router,
    private val signUpUseCase: SignUpUseCase,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _ldShowToastText: MutableLiveData<String> = MutableLiveData()
    val ldShowToastText: LiveData<String> = _ldShowToastText

    fun onSignInBtn() {
        mRouter.exit()
    }

    @SuppressLint("CheckResult")
    fun onSignUpBtn(email: String, password: String, confirmPassword: String) {
        signUpUseCase.execute(email, password, confirmPassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { resultState ->
                when (resultState!!) {
                    SignUpState.INVALID_PASSWORD -> {
                        _ldShowToastText.value =  resourceProvider
                            .getStringRes(R.string.the_password_must_contain_at_least)
                    }
                    SignUpState.PASSWORDS_ARE_NOT_EQUAL -> {
                        _ldShowToastText.value =  resourceProvider
                            .getStringRes(R.string.passwords_are_not_equal)
                    }
                    SignUpState.EMPTY_EMAIL -> {
                        _ldShowToastText.value = resourceProvider
                            .getStringRes(R.string.email_is_empty)
                    }
                    SignUpState.SIGN_UP_FAILED -> {
                        _ldShowToastText.value =  resourceProvider
                            .getStringRes(R.string.failed_to_create_an_account)
                    }
                    SignUpState.SIGN_UP_SUCCESSFUL -> {
                        mRouter.newRootScreen(Screens.homeFragment())
                    }
                }
            }
    }
}