package com.example.innowisepexelstestapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.databinding.FragmentDetailsBinding
import com.example.innowisepexelstestapp.di.injectViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.DetailsViewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val mBinding by viewBinding(FragmentDetailsBinding::bind)
    private val mVm: DetailsViewModel by injectViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() = with(mBinding) {
        downloadBtn.setOnClickListener {
            mVm.onDownloadBtn()
        }
        backBtn.setOnClickListener {
            mVm.onBackBtn()

        }
        favoriteBtn.setOnClickListener {
            mVm.onFavoriteBtn()
        }
    }
}