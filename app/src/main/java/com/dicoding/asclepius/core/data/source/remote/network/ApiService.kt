package com.dicoding.asclepius.core.data.source.remote.network

import com.dicoding.asclepius.core.data.source.remote.response.MedicalNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
     fun medicalNews(
        @Query("q") query: String,
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("apiKey") apiKey: String
    ): Call<MedicalNews>
}