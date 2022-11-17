package com.example.task

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {


    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl("https://catfact.ninja/")
            .addConverterFactory(GsonConverterFactory.create())

            .build()
    }


}
