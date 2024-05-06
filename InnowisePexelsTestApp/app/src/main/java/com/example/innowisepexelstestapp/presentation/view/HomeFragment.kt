package com.example.innowisepexelstestapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.databinding.FragmentHomeBinding
import com.example.innowisepexelstestapp.di.injectViewModel
import com.example.innowisepexelstestapp.model.Category
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.presentation.rv.RvCategoryAdapter
import com.example.innowisepexelstestapp.presentation.rv.RvPhotoAdapter
import com.example.innowisepexelstestapp.presentation.viewmodel.HomeViewModel

class HomeFragment : Fragment(R.layout.fragment_home),
    RvPhotoAdapter.ClickListener,
    RvCategoryAdapter.ClickListener {

    private val mBinding by viewBinding(FragmentHomeBinding::bind)
    private val mVm: HomeViewModel by injectViewModel() //todo попробовать by viewmodels
    private val mPhotoAdapter: RvPhotoAdapter = RvPhotoAdapter(this,
        showAuthorName = false)
    private val mCategoryAdapter: RvCategoryAdapter = RvCategoryAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewsPresets()
        setupListeners()
        setupObservers()
    }

    override fun onClickPhoto(photoPexels: PhotoPexels) {
        mVm.onClickPhoto(photoPexels)
    }

    override fun onClickCategory(category: Category, position: Int) {
        mVm.onClickCategory(category, position)
    }

    private fun setViewsPresets() = with(mBinding) {
        homeRv.layoutManager = StaggeredGridLayoutManager(2,
            StaggeredGridLayoutManager.VERTICAL)
        categoryRv.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)

        homeRv.adapter = mPhotoAdapter
        categoryRv.adapter = mCategoryAdapter
        categoryRv.setItemViewCacheSize(7)
    }

    private fun setupListeners() = with(mBinding) {
        var isLoading = false
        bnvFavorite.setOnClickListener {
            mVm.navigateToFavorite()
        }
        tvTryAgain.setOnClickListener {
            mVm.addPhotos()
            mVm.setCategories()
        }
        searchBarCloseIcon.setOnClickListener {
            mVm.onSearchBarCloseIcon()
        }
        searchBarEditText.doAfterTextChanged {
            mVm.doAfterTextChanged(it!!)
        }
        searchBarSearchIcon.setOnClickListener {
            mVm.addQueryPhotos(searchBarEditText.text.toString().trim())
        }
        homeRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!isLoading) {
                    isLoading = true
                        mVm.onScrolledRv(recyclerView, searchBarEditText.text.toString().trim())
                    isLoading = false
                }
            }
        })
    }

    private fun setupObservers() = with(mBinding) {
        mVm.ldOnCloseButton.observe(viewLifecycleOwner) {
            searchBarEditText.text.clear()
            searchBarEditText.clearFocus()
            mCategoryAdapter.categories.forEach { category ->
                category.isActive = false
            }
            mCategoryAdapter.notifyDataSetChanged()
        }
        mVm.ldSearchBarCloseIconVisibility.observe(viewLifecycleOwner) {
            searchBarCloseIcon.visibility = it
        }
        mVm.ldIvNoNetworkVisibility.observe(viewLifecycleOwner) {
            ivNonetwork.visibility = it
        }
        mVm.ldTvTryAgainVisibility.observe(viewLifecycleOwner) {
            tvTryAgain.visibility = it
        }
        mVm.ldAddPhotoList.observe(viewLifecycleOwner) {
            mPhotoAdapter.addPhotoList(it)
        }
        mVm.ldCreateNewPhotoList.observe(viewLifecycleOwner) {
            mPhotoAdapter.createNewPhotoList(it)
        }
        mVm.ldAddCategoryList.observe(viewLifecycleOwner) {
            mCategoryAdapter.addCategories(it)
        }
        mVm.ldProgressBarVisibility.observe(viewLifecycleOwner) {
            progressBar.visibility = it
        }
        mVm.ldShowAnim.observe(viewLifecycleOwner) {
            homeRv.startAnimation(it)
        }
        mVm.ldSetActiveCategory.observe(viewLifecycleOwner) {
            mCategoryAdapter.categories.forEach { category ->
                category.isActive = false
            }
            mCategoryAdapter.categories[it].isActive = true
            mCategoryAdapter.notifyDataSetChanged()
        }
        mVm.ldSetEditTextCategoryName.observe(viewLifecycleOwner) {
            searchBarEditText.text = it
        }
    }
}