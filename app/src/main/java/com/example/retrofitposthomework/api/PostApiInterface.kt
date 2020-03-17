package com.example.retrofitposthomework.api

import com.example.retrofitposthomework.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface PostApiInterface {
    @GET ("posts")
    fun getPost(): Call<List<Post>>
}