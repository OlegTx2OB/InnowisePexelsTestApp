package com.example.innowisepexelstestapp.repository

import android.util.Log
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
//GET https://api.pexels.com/v1/curated?page=1&per_page=15
const val GET_CURATED_URL = "https://api.pexels.com/v1/curated"
const val KEY = "1yRhwLBuxcoFBCLNlvFi7d00HeHhnv7fOzvYXsYTrbVBUV0RFk157lqx"
class NetworkManagerImpl(private val mClient: OkHttpClient): NetworkManager {


    private val mGetCuratedRequest = Request.Builder()
        .url(GET_CURATED_URL)
        .addHeader("Authorization", KEY)
        .get()
        .build()

    override fun getCuratedPhotos(): List<PhotoPexels> {//todo переделать
//        val list = mutableListOf<PhotoPexels>()
//        mClient.newCall(mGetCuratedRequest).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                e.printStackTrace()
//                //todo тут сделать
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                if (response.isSuccessful) {
//                    val photoListType = object : TypeToken<List<PhotoPexels>>() {}.type
//                    list.addAll(Gson().fromJson(response.body!!.string(), photoListType))
//                } else {
//                    Log.e( "tag", response.code.toString()) //todo мб убрать это или поменять
//                }
//            }
//
//        })
        return listOf()
    }
}