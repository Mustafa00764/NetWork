package com.example.network.data.remote

import com.example.network.model.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiSevice {

    @GET("/posts")
    fun getAllPosts():Call<ArrayList<Post>>

    @GET("/posts/{id}")
    fun getPostById(@Path("id") id:Int):Call<Post>

    @POST("/posts")
    fun createPost(@Body post:Post):Call<Post>


    @PUT("/posta/{id}")
    fun updatePost(@Path("id") id: Int,@Body post: Post ):Call<Post>


    @DELETE("/posts/{id}")
    fun deletePost(@Path("id") id: Int):Call<Post>

}