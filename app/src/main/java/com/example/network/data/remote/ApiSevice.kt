package com.example.network.data.remote

import com.example.network.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface ApiSevice {

    @GET("/posts")
    fun getAllPosts():Call<ArrayList<Post>>

}