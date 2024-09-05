package com.example.innowisepexelstestapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.innowisepexelstestapp.presentation.navigation.Screens
import com.example.innowisepexelstestapp.usecase.SignInUseCase
import com.example.innowisepexelstestapp.util.ResourceProvider
import com.github.terrakok.cicerone.Router
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

    fun onSignInBtn(email: String, password: String) {
        signInUseCase.execute(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { resultState ->
                when (resultState!!) {
                    SIGN_IN_FAILED
                }
            }
    }

}