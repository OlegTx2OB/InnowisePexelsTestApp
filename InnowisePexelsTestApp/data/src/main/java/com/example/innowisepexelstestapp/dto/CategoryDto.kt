package com.example.innowisepexelstestapp.dto

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("title") val name: String
)