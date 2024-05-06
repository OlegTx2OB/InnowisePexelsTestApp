package com.example.innowisepexelstestapp.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.databinding.FragmentDetailsBinding
import com.example.innowisepexelstestapp.di.injectViewModel
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.presentation.viewmodel.DetailsViewModel
import com.example.innowisepexelstestapp.util.findArgument
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val mBinding by viewBinding(FragmentDetailsBinding::bind)
    private val mVm: DetailsViewModel by injectViewModel() //todo провериь и без injectViewModel

    private val photoPexels: PhotoPexels by lazy (LazyThreadSafetyMode.NONE) {
        findArgument("photoPexels")!! }
    private val isItLikedPhotoArg: Boolean by lazy (LazyThreadSafetyMode.NONE) {
        findArgument("isItLikedPhoto")!! }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setViewsPresets()
        setupObservers()
    }

    private fun setViewsPresets() = with(mBinding) {
        if(isItLikedPhotoArg) {
            favoriteBtn.setImageResource(R.drawable.ic_favorite_active)
        }

        Picasso.get()
            .load(photoPexels.sources.original)
            .placeholder(R.drawable.ic_imagestub)
            .into(image)
    }

    private fun setupListeners() = with(mBinding) {
        var isItLikedPhoto = isItLikedPhotoArg

        favoriteBtn.setOnClickListener {
            mVm.onFavoriteBtn(isItLikedPhoto, favoriteBtn, photoPexels)
            isItLikedPhoto = !isItLikedPhoto
        }

        image.setOnViewDragListener { dx, dy ->
            if (dx != 0f || dy != 0f) {
                image.setScale(1.0f, true)
            }
        }
        authorName.text = photoPexels.photographer

        downloadBtn.setOnClickListener {
            mVm.onDownloadBtn(photoPexels.sources.original)
        }
        backBtn.setOnClickListener {
            mVm.onBackBtn()
        }
    }

    private fun setupObservers() {
        mVm.ldShowToast.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

}