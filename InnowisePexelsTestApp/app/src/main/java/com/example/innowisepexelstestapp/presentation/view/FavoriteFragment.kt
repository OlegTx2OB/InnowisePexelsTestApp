package com.example.innowisepexelstestapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.databinding.FragmentFavoriteBinding
import com.example.innowisepexelstestapp.di.injectViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    private val mBinding by viewBinding(FragmentFavoriteBinding::bind)
    private val mVm: FavoriteViewModel by injectViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() = with(mBinding) {

    }
}