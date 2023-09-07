package com.example.network.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiClient {

    private val isTerter = true
    private val SERVER_DEVELOPMENT = "https://jsonplaceholder.typicode.com"
    private val SERVER_PRODUCTION = "https://jsonplaceholder.typicode.com"


    private val retrofit = Retrofit.Builder()
        .baseUrl(mybaseURL())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiSevice = retrofit.create(ApiSevice::class.java)

    private fun mybaseURL():String{
        if (isTerter){
            return SERVER_DEVELOPMENT
        }else{
            return SERVER_PRODUCTION
        }
    }

}