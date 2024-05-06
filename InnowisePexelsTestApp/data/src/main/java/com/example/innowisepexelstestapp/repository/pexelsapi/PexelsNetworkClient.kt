package com.example.innowisepexelstestapp.repository.pexelsapi

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

const val KEY = "1yRhwLBuxcoFBCLNlvFi7d00HeHhnv7fOzvYXsYTrbVBUV0RFk157lqx"

const val URL_GET_CURATED = "https://api.pexels.com/v1/curated"
const val URL_GET_FEATURED_COLLECTIONS = "https://api.pexels.com/v1/collections/featured"
const val URL_GET_SEARCH = "https://api.pexels.com/v1/search"

const val PER_PAGE_FEATURED_COLLECTIONS = "per_page=7"
const val PER_PAGE_30 = "per_page=30"
const val PAGE = "page="
const val QUERY = "query="

class PexelsNetworkClient(private val mClient: OkHttpClient) {

    private var pageCurated: Int = 1
    private var pageQuery: Int = 1
    fun getResponseWithCuratedPhotos(): Response {
        val request = Request.Builder()
            .url("$URL_GET_CURATED?$PAGE${pageCurated++}&$PER_PAGE_30")
            .header("Authorization", KEY)
            .get()
            .build()
        return mClient.newCall(request).execute()
    }

    fun getResponseWithFeaturedCategories(): Response {
        val request = Request.Builder()
            .url("$URL_GET_FEATURED_COLLECTIONS?$PER_PAGE_FEATURED_COLLECTIONS")
            .header("Authorization", KEY)
            .get()
            .build()
        return mClient.newCall(request).execute()
    }

    fun getResponseWithQueryPhotos(query: String): Response {
        val request = Request.Builder()
            .url("$URL_GET_SEARCH?$QUERY$query&$PAGE${pageQuery++}&$PER_PAGE_30")
            .header("Authorization", KEY)
            .get()
            .build()
        return mClient.newCall(request).execute()
    }

}