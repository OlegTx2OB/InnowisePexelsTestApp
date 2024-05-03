package com.example.innowisepexelstestapp.repository.api

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

const val KEY = "1yRhwLBuxcoFBCLNlvFi7d00HeHhnv7fOzvYXsYTrbVBUV0RFk157lqx"
const val URL_GET_30_CURATED = "https://api.pexels.com/v1/curated?per_page=30"

class PexelsApiClient(private val mClient: OkHttpClient) { //todo мб изменить это убогое имя класса

    fun getResponseWithCuratedPhotos(): Response {
        val request = Request.Builder()
            .url(URL_GET_30_CURATED)
            .header("Authorization", KEY)
            .get()//todo мб убрать, мб лишнее
            .build()

        return mClient.newCall(request).execute()
    }

}