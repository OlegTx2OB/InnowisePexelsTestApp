package com.example.innowisepexelstestapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.databinding.FragmentHomeBinding
import com.example.innowisepexelstestapp.di.injectViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.HomeViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val mBinding by viewBinding(FragmentHomeBinding::bind)
    private val mVm: HomeViewModel by injectViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() = with(mBinding) {

    }
}