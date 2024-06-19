package com.example.mynamesactions.api

import retrofit2.http.GET

interface NamesApi {

    companion object {
        const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
    }

    @GET("hiring.json")
    suspend fun getNames(): List<NamesDto>
}