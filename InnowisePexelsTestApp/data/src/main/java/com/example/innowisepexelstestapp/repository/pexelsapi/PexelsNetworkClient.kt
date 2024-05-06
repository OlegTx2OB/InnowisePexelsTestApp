package com.example.innowisepexelstestapp.repository.pexelsapi

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

const val KEY = "1yRhwLBuxcoFBCLNlvFi7d00HeHhnv7fOzvYXsYTrbVBUV0RFk157lqx"

const val URL_GET_CURATED = "https://api.pexels.com/v1/curated?page="
const val URL_GET_FEATURED_COLLECTIONS = "https://api.pexels.com/v1/collections/featured"

const val PER_PAGE_FEATURED_COLLECTIONS = "?per_page=7"
const val PER_PAGE_CURATED = "&per_page=30"

class PexelsNetworkClient(private val mClient: OkHttpClient) {

    private var page: Int = 1
    fun getResponseWithCuratedPhotos(): Response {
        val request = Request.Builder()
            .url("$URL_GET_CURATED${page++}$PER_PAGE_CURATED")
            .header("Authorization", KEY)
            .get()
            .build()
        return mClient.newCall(request).execute()
    }

    fun getResponseWithFeaturedCategories(): Response {
        val request = Request.Builder()
            .url("$URL_GET_FEATURED_COLLECTIONS$PER_PAGE_FEATURED_COLLECTIONS")
            .header("Authorization", KEY)
            .get()
            .build()
        return mClient.newCall(request).execute()
    }

}