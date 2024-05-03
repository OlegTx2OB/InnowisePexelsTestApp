package com.example.innowisepexelstestapp.repository.network.api

import com.example.innowisepexelstestapp.repository.network.internal.KEY
import com.example.innowisepexelstestapp.repository.network.internal.URL_GET_30_CURATED
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class PexelsCollectionsNetworkClient(private val mClient: OkHttpClient) { //todo мб изменить это убогое имя класса

    fun getResponseWithCuratedPhotos(): Response {
        val request = Request.Builder()
            .url(URL_GET_30_CURATED)
            .header("Authorization", KEY)
            .get()//todo мб убрать, мб лишнее
            .build()

        return mClient.newCall(request).execute()
    }

}