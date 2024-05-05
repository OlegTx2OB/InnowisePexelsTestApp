package com.example.innowisepexelstestapp.repository.pexelsapi

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

const val KEY = "1yRhwLBuxcoFBCLNlvFi7d00HeHhnv7fOzvYXsYTrbVBUV0RFk157lqx"
const val URL_GET_CURATED = "https://api.pexels.com/v1/curated?page="
const val PER_PAGE = "&per_page=30"

class PexelsNetworkClient(private val mClient: OkHttpClient) {

    private var page: Int = 1
    fun getResponseWithCuratedPhotos(): Response {
        val request = Request.Builder()
            .url("$URL_GET_CURATED${page++}$PER_PAGE")
            .header("Authorization", KEY)
            .get()
            .build()
        return mClient.newCall(request).execute()
    }

}