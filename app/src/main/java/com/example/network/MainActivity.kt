package com.example.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.network.data.remote.ApiClient
import com.example.network.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        ApiClient.apiSevice.getAllPosts().enqueue(object :Callback<ArrayList<Post>>{
            override fun onResponse(
                call: Call<ArrayList<Post>>,
                response: Response<ArrayList<Post>>
            ) {
                if (response.isSuccessful){
                    Log.d("isSuccessful","onResponse:${response.body()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}