package com.example.innowisepexelstestapp.util

import android.content.Context

class ResourceProvider(private val mAppContext: Context) {

    fun getStringRes(id: Int) : String {
        return mAppContext.getString(id)
    }
}