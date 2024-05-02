package com.example.data.repository

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

const val GET_URL = ""
//class NetworkManager @Inject constructor(
//    private val client: OkHttpClient,
//    private val request: Request
//) {
//
//
//    val request = Request.Builder().url(url).get().build()
//
//    fun getEndpoint() {
//        val client = OkHttpClient()
//        val url = "https://api.pexels.com/v1/search"
//        val request = Request.Builder().url(url).get().build()
//
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                e.printStackTrace()
//                TODO("Not yet implemented")
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                if (response.isSuccessful) {
//                    response.body!!.string()
//                } else {
//                    //todo
//                }
//            }
//
//        })
//    }
//}