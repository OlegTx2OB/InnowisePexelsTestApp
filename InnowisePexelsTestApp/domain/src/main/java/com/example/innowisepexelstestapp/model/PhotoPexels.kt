package com.example.innowisepexelstestapp.model

import com.google.gson.annotations.SerializedName

data class PhotoPexels(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    val photographerURL: String,
    val photographerID: String,
    val averageColor: String,
    val sources: ImageSources,
    val liked: Boolean,
    val description: String
)
data class ImageSources(
    val original: String,
    val extraLarge: String,
    val large: String,
    val medium: String,
    val small: String,
    val portrait: String,
    val landscape: String,
    val tiny: String
)