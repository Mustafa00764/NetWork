package com.example.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.network.data.remote.ApiClient
import com.example.network.model.Post
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivity : AppCompatActivity() {
    lateinit var bSave:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        initViews()
    }

    private fun initViews() {
        val etTitle:EditText=findViewById(R.id.et_title)
        val etBody:EditText=findViewById(R.id.et_body)
        bSave=findViewById(R.id.b_save)
        
        bSave.setOnClickListener { 
            val title = etTitle.text.toString()
            val body = etBody.text.toString()
            
            if (title.isEmpty() || body.isEmpty()){
                Toast.makeText(this,"Поле пустое", Toast.LENGTH_SHORT)
            }else{
                val post = Post(3,1,title,body)
                savePost(post)
            }
        }
    }

    private fun savePost(post: Post) {
        bSave.text="Saving ... "
        ApiClient.apiSevice.createPost(post).enqueue(object :Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful){
                    bSave.text= "Succesfully saved"
                    bSave.isEnabled = false
                    Toast.makeText(this@AddActivity,"${response.body()}",Toast.LENGTH_SHORT)
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                bSave.text = "Error"

            }
        })

    }
}