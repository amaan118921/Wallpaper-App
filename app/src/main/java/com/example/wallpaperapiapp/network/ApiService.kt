package com.example.wallpaperapiapp.network

import com.example.wallpaperapiapp.model.Info
import com.example.wallpaperapiapp.model.Items
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://pixabay.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface ApiService  {
    @GET("api/?key=21841907-55025c06f5ffcb57a84b8e7d5")
    suspend fun getImages(@Query("q")search: String, @Query("image_type")imageType: String): Info
}

object API {
    val retrofitService:ApiService by lazy { retrofit.create(ApiService::class.java) }
}