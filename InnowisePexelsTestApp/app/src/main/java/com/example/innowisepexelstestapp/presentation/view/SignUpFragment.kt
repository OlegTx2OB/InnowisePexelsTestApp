package com.example.innowisepexelstestapp.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.databinding.FragmentSignUpBinding
import com.example.innowisepexelstestapp.di.injectViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.SignUpViewModel

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val mViewBinding by viewBinding(FragmentSignUpBinding::bind)
    private val mViewModel: SignUpViewModel by injectViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setViewsPresets()
        setupObservers()
    }

    private fun setViewsPresets(): Unit = with(mViewBinding) {

    }

    private fun setupListeners(): Unit = with(mViewBinding) {
        tvSignIn.setOnClickListener {
            mViewModel.onSignInBtn()
        }
        btnSignUn.setOnClickListener {
            mViewModel.onSignUpBtn(
                etEmail.text.toString(),
                etPassword.text.toString(),
                etConfirmPassword.text.toString()
            )
        }
    }

    private fun setupObservers() : Unit = with(mViewModel) {
        ldShowToastText.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }
}