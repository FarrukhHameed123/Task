package com.example.task

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("fact")
    fun getPosts(): Call<PostModel>
}
