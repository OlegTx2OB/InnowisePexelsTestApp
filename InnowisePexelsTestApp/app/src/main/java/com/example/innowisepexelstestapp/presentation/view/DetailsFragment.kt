package com.example.innowisepexelstestapp.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.databinding.FragmentDetailsBinding
import com.example.innowisepexelstestapp.di.injectViewModel
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.presentation.viewmodel.DetailsViewModel
import com.example.innowisepexelstestapp.util.findArgument
import com.github.chrisbanes.photoview.OnViewDragListener
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val mBinding by viewBinding(FragmentDetailsBinding::bind)
    private val mVm: DetailsViewModel by injectViewModel()
    private val photoPexels: PhotoPexels by lazy { findArgument("photoPexels")!! }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setViewsPresets()
    }

    private fun setViewsPresets() = with(mBinding) {
        Picasso.get()
            .load(photoPexels.sources.original)
            .placeholder(R.drawable.im_dark_stub)
            .into(image)//todo исправить
    }

    private fun setupClickListeners() = with(mBinding) {

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
        favoriteBtn.setOnClickListener {
            mVm.onFavoriteBtn()
        }
    }
}