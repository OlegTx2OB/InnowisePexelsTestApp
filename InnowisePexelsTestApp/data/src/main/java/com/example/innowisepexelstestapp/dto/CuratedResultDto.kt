package com.example.innowisepexelstestapp.dto

import com.google.gson.annotations.SerializedName

data class CuratedResultDto(
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val itemsPerPage: String,
    @SerializedName("photos") val photos: List<PhotoPexelsDto>,
    @SerializedName("prev_page") val previousPageURL: String?,
    @SerializedName("next_page") val nextPageURL: String?
)