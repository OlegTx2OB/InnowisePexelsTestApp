package com.example.innowisepexelstestapp.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.databinding.FragmentSplashScreenBinding
import com.example.innowisepexelstestapp.di.injectViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.SplashScreenViewModel


@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen)  {
    private val mBinding by viewBinding(FragmentSplashScreenBinding::bind)
    private val mVm: SplashScreenViewModel by injectViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fall_animation)
        mBinding.logo.startAnimation(animation)
    }

}