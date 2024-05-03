package com.example.innowisepexelstestapp.model

import com.google.gson.annotations.SerializedName

data class CuratedResult(
    val page: Int,
    val itemsPerPage: String,
    val photos: List<PhotoPexels>,
    val previousPageURL: String?,
    val nextPageURL: String?
)