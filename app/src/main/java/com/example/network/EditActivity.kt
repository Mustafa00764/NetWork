package com.example.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.core.widget.ContentLoadingProgressBar
import com.example.network.data.remote.ApiClient
import com.example.network.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditActivity : AppCompatActivity() {
    var id=0
    lateinit var loadingProgressBar: ProgressBar
    lateinit var etTitle:EditText
    lateinit var etBody:EditText
    lateinit var post:Post
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        initViews()
    }

    private fun initViews() {
        id=intent.getIntExtra("id",1)
        loadingProgressBar = findViewById(R.id.pr_loading)
        etTitle = findViewById(R.id.et_title)
        etBody = findViewById(R.id.et_body)
        loadPost()
    }

    private fun loadPost() {
        showLoading()
        ApiClient.apiSevice.getPostById(id).enqueue(object :Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful){
                    post=response.body()!!
                    etTitle.setText(post.title)
                    etBody.setText(post.body)
                }
                hideLoading()
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                hideLoading()
            }
        })

    }
    fun showLoading(){
        loadingProgressBar.visibility= View.VISIBLE
    }
    fun hideLoading(){
        loadingProgressBar.visibility= View.GONE
    }
}