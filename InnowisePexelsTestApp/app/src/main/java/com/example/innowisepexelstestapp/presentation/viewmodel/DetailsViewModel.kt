package com.example.innowisepexelstestapp.presentation.viewmodel

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.usecase.DeleteImageFromRoomUseCase
import com.example.innowisepexelstestapp.usecase.SaveImageIntoRoomUseCase
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val mRouter: Router, private val mContext: Context) : ViewModel() {

    private val _ldShowToast: MutableLiveData<String> = MutableLiveData()
    val ldShowToast: LiveData<String> = _ldShowToast

    @Inject
    lateinit var saveImageIntoRoomUseCase: SaveImageIntoRoomUseCase //todo попробовать закинуть в конструктор

    @Inject
    lateinit var deleteImageIntoRoomUseCase: DeleteImageFromRoomUseCase

    fun onDownloadBtn(imageUrl: String) {
        TODO("Not yet implemented")
    }

    fun onBackBtn() {
        mRouter.exit()
    }

    fun onFavoriteBtn(isItLikedPhoto: Boolean, view: ImageView, photoPexels: PhotoPexels) {
        if(isItLikedPhoto) {
            view.setImageResource(R.drawable.ic_favorite_inactive)
            deleteImageIntoRoomUseCase.execute(photoPexels)
            _ldShowToast.value = mContext.getString(R.string.deleted)
        } else {
            view.setImageResource(R.drawable.ic_favorite_active)
            saveImageIntoRoomUseCase.execute(photoPexels)
            _ldShowToast.value = mContext.getString(R.string.saved)
        }

    }
}