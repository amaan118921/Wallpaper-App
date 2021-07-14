package com.example.wallpaperapiapp.model

import com.squareup.moshi.Json

data class Info(
    val hits: List<Items>
)

data class Items (
        val id: String, @Json(name="previewURL")val url: String, @Json(name="webformatURL")val webUrl: String,
        val largeImageURL: String, val views: String, val downloads: String, val likes: String, val user: String
        )