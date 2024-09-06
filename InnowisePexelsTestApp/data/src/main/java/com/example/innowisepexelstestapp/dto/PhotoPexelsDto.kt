package com.example.innowisepexelstestapp.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PhotoPexelsDto(
    @PrimaryKey
    @SerializedName("id") val id: Int = 0,
    @SerializedName("photographer") val photographer: String = "",
    @SerializedName("src") val sources: ImageSourcesDto = ImageSourcesDto(),
)
data class ImageSourcesDto(
    @SerializedName("original") val original: String = "",
    @SerializedName("medium") val medium: String = "",
    @SerializedName("small") val small: String = "",
)