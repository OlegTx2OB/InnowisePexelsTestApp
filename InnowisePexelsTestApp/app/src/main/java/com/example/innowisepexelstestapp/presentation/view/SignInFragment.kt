package com.example.innowisepexelstestapp.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.databinding.FragmentHomeBinding
import com.example.innowisepexelstestapp.databinding.FragmentSignInBinding
import com.example.innowisepexelstestapp.di.injectViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.HomeViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.SignInViewModel
import com.squareup.picasso.Picasso

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val mViewBinding by viewBinding(FragmentSignInBinding::bind)
    private val mViewModel: SignInViewModel by injectViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setViewsPresets()
        setupObservers()
    }

    private fun setViewsPresets(): Unit = with(mViewBinding) {

    }

    private fun setupListeners(): Unit = with(mViewBinding) {
        tvSignUp.setOnClickListener {
            mViewModel.onSignUpBtn()
        }
    }

    private fun setupObservers() {

    }
}