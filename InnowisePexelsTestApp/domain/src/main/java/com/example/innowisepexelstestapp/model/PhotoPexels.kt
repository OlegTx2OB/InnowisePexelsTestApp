package com.example.innowisepexelstestapp.model

import java.io.Serializable

data class PhotoPexels(
    val id: Int,
    val photographer: String,
    val sources: ImageSources,
) : Serializable
data class ImageSources(
    val original: String,
    val medium: String,
    val small: String,
) : Serializable