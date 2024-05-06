package com.example.innowisepexelstestapp.model

import java.io.Serializable

data class Category(
    val name: String,
    var isActive: Boolean
) : Serializable