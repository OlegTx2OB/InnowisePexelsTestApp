package com.example.innowisepexelstestapp.util

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils

class ResourceProvider(private val mAppContext: Context) {

    fun getStringRes(id: Int): String {
        return mAppContext.getString(id)
    }

    fun getAnim(id: Int): Animation {
        return AnimationUtils.loadAnimation(mAppContext, id)
    }

}