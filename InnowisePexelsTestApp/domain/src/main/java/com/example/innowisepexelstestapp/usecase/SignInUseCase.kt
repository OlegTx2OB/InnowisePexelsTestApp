package com.example.innowisepexelstestapp.usecase

import com.example.innowisepexelstestapp.repository.SignInSignUpManager
import io.reactivex.Single

enum class SignInState {
    SIGN_IN_FAILED,
    SIGN_IN_SUCCESSFUL
}

class SignInUseCase(private val signInSignUpManager: SignInSignUpManager) {

    @Suppress("CheckResult")
    fun execute(email: String, password: String): Single<SignInState> {
        return Single.create<SignInState> { emitter ->
            signInSignUpManager.signInUser(email, password).subscribe { isUserSignedUp ->
                if (isUserSignedUp) {
                    emitter.onSuccess(SignInState.SIGN_IN_SUCCESSFUL)
                } else {
                    emitter.onSuccess(SignInState.SIGN_IN_FAILED)
                }
            }
        }
    }
}