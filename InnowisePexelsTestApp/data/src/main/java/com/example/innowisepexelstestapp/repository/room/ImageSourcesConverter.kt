package com.example.innowisepexelstestapp.repository.room

import androidx.room.TypeConverter
import com.example.innowisepexelstestapp.dto.ImageSourcesDto
import com.google.gson.Gson

object ImageSourcesConverter {

    @TypeConverter
    @JvmStatic
    fun fromImageSourcesEntity(imageSources: ImageSourcesDto): String {
        return Gson().toJson(imageSources)
    }

    @TypeConverter
    @JvmStatic
    fun toImageSourcesEntity(imageSourcesString: String): ImageSourcesDto {
        return Gson().fromJson(imageSourcesString, ImageSourcesDto::class.java)
    }
}