package com.example.spotifly.api

import com.example.spotifly.data.Track
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

interface ApiService {

    @GET
    fun get(@Url url: String): Call<ResponseBody>

    @GET("/")
    fun getIndex(): Call<ResponseBody>

    @GET("token")
    fun getToken(): Call<ResponseBody>

    @GET("tracks")
    fun getTracks(
        @Header("query") query: String,
        @Header("limit") limit: Int = 5,
        @Header("offset") offset: Int = 0
    ): Call<List<Track>>

    companion object {
        val service: ApiService by lazy {
            Retrofit.Builder()
                .baseUrl("https://uselessapi.hypernova101.repl.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}