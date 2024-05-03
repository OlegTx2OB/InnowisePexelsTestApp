package com.example.innowisepexelstestapp.dto

import com.google.gson.annotations.SerializedName

data class PhotoPexelsDto(
    @SerializedName("id") val id: Int,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("url") val url: String,
    @SerializedName("photographer") val photographer: String,
    @SerializedName("photographer_url") val photographerUrl: String,
    @SerializedName("photographer_id") val photographerId: String,
    @SerializedName("avg_color") val averageColor: String,
    @SerializedName("src") val sources: ImageSourcesDto,
    @SerializedName("liked") val liked: Boolean,
    @SerializedName("alt") val description: String
)
data class ImageSourcesDto(
    @SerializedName("original") val original: String,
    @SerializedName("large2x") val extraLarge: String,
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("small") val small: String,
    @SerializedName("portrait") val portrait: String,
    @SerializedName("landscape") val landscape: String,
    @SerializedName("tiny") val tiny: String
)