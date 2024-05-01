package com.example.innowisepexelstestapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.databinding.FragmentSplashScreenBinding
import com.example.innowisepexelstestapp.di.injectViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.SplashScreenViewModel

class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen)  {
    private val mBinding by viewBinding(FragmentSplashScreenBinding::bind)
    private val mVm: SplashScreenViewModel by injectViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}