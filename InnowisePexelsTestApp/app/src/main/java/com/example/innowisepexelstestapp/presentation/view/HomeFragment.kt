package com.example.innowisepexelstestapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.databinding.FragmentHomeBinding
import com.example.innowisepexelstestapp.di.injectViewModel
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.presentation.rv.RvPhotoAdapter
import com.example.innowisepexelstestapp.presentation.viewmodel.HomeViewModel

class HomeFragment : Fragment(R.layout.fragment_home), RvPhotoAdapter.ClickListener {
    private val mBinding by viewBinding(FragmentHomeBinding::bind)
    private val mVm: HomeViewModel by injectViewModel() //todo попробовать by viewmodels
    private val mAdapter: RvPhotoAdapter = RvPhotoAdapter(this, showAuthorName = false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewsPresets()
        mVm.setPhotos()         //todo сделать нормальную подачу фотографий
        setupListeners()
        setupObservers()
    }

    override fun onClickPhoto(photoPexels: PhotoPexels) {
        mVm.onClickPhoto(photoPexels)
    }

    private fun setViewsPresets() = with(mBinding) {
        homeRv.layoutManager = StaggeredGridLayoutManager(2,
            StaggeredGridLayoutManager.VERTICAL)
        homeRv.adapter = mAdapter
    }

    private fun setupListeners() = with(mBinding) {
        var isLoading = false
        homeRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!isLoading) {
                    val layoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager
                    val lastVisibleItemPositions = layoutManager
                        .findLastVisibleItemPositions(null)
                    val totalItemCount = layoutManager.itemCount
                    val maxVisibleItemPosition = lastVisibleItemPositions.maxOrNull()

                    if (maxVisibleItemPosition == totalItemCount - 1) {
                        isLoading = true
                        mVm.setPhotos()
                        isLoading = false
                    }
                }
            }
        })
        bnvFavorite.setOnClickListener {
            mVm.navigateToFavorite()
        }
        tvTryAgain.setOnClickListener {
            mVm.setPhotos()
        }
        searchBarCloseIcon.setOnClickListener {
            mVm.onSearchBarCloseIcon()
        }
        searchBarEditText.doAfterTextChanged {
            mVm.doAfterTextChanged(it!!)
        }
    }

    private fun setupObservers() = with(mBinding) {
        mVm.ldSearchBarEditTextAction.observe(viewLifecycleOwner) {
            searchBarEditText.text.clear()
            searchBarEditText.clearFocus()
        }
        mVm.ldSearchBarCloseIconVisibility.observe(viewLifecycleOwner) {
            searchBarCloseIcon.visibility = it
        }
        mVm.ldAddPhotoList.observe(viewLifecycleOwner) {
            mAdapter.addPhotoListForHomeScreen(it)
        }
        mVm.ldIvNoNetworkVisibility.observe(viewLifecycleOwner) {
            ivNonetwork.visibility = it
        }
        mVm.ldTvTryAgainVisibility.observe(viewLifecycleOwner) {
            tvTryAgain.visibility = it
        }
        mVm.ldAddPhotoList.observe(viewLifecycleOwner) {
            mAdapter.addPhotoListForHomeScreen(it)
        }
        mVm.ldProgressBarVisibility.observe(viewLifecycleOwner) {
            progressBar.visibility = it
        }
        mVm.ldShowAnim.observe(viewLifecycleOwner) {
            homeRv.startAnimation(it)
        }
    }
}