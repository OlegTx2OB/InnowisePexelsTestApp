package com.example.innowisepexelstestapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.databinding.FragmentFavoriteBinding
import com.example.innowisepexelstestapp.di.injectViewModel
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.presentation.rv.RvPhotoAdapter
import com.example.innowisepexelstestapp.presentation.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment(R.layout.fragment_favorite), RvPhotoAdapter.ClickListener {

    private val mBinding by viewBinding(FragmentFavoriteBinding::bind)
    private val mVm: FavoriteViewModel by injectViewModel()
    private val mAdapter: RvPhotoAdapter = RvPhotoAdapter(this, showAuthorName = true)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewsPresets()
        mVm.setPhotos()
        setupListeners()
        setupObservers()
    }

    override fun onClickPhoto(photoPexels: PhotoPexels) {
        mVm.onClickPhoto(photoPexels)
    }

    private fun setViewsPresets() = with(mBinding) {
        favoriteRv.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        favoriteRv.adapter = mAdapter
    }

    private fun setupListeners() = with(mBinding) {
        bnvHome.setOnClickListener {
            mVm.navigateToHome()
        }
        tvExplore.setOnClickListener {
            mVm.navigateToHome()
        }
    }

    private fun setupObservers() = with(mBinding) {
        mVm.ldAddPhotoList.observe(viewLifecycleOwner) {
            mAdapter.addPhotoListForFavoriteScreen(it)
        }
        mVm.ldShowAnim.observe(viewLifecycleOwner) {
            favoriteRv.startAnimation(it)
        }
        mVm.ldTvNoFavoritesVisibility.observe(viewLifecycleOwner) {
            tvNoFavorites.visibility = it
        }
        mVm.ldTvExploreVisibility.observe(viewLifecycleOwner) {
            tvExplore.visibility = it
        }
    }

}