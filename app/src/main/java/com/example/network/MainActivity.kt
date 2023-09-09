package com.example.network

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.network.adapter.PostAdapter
import com.example.network.data.remote.ApiClient
import com.example.network.model.Post
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var myAdapter:PostAdapter
    lateinit var postList:ArrayList<Post>
    lateinit var loadingProgressBar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        val rvPost = findViewById<RecyclerView>(R.id.rv_post)
        val fbAdd:FloatingActionButton=findViewById(R.id.fb_add)
        loadingProgressBar=findViewById(R.id.pr_loading)
        myAdapter= PostAdapter()
        rvPost.adapter=myAdapter
        loadList()


        myAdapter.itemlongClick={ position ->
            val dialog = AlertDialog.Builder(this)

            dialog.setTitle("What do you want")
            dialog.setMessage("Bu yerda sizning reklamangiz bo'lishi mumkin edi!")
            dialog.setPositiveButton("Edit", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    //edit
                    val intent=Intent(this@MainActivity,EditActivity::class.java)
                    intent.putExtra("id",postList[position].id)
                    startActivity(intent)
                }

            })
            dialog.setNegativeButton("Delete", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    //delete
                    deletePost(postList[position].id)
                }

            })

            dialog.show()


        }


        fbAdd.setOnClickListener{
            val intent = Intent(this,AddActivity::class.java)
            startActivity(intent)
        }


    }
    private fun deletePost(id:Int){
        showLoading()
        ApiClient.apiSevice.deletePost(id).enqueue(object :Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                hideLoading()
                loadList()
                Toast.makeText(this@MainActivity,"post o'chirildi",Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                hideLoading()
            }
        })
    }

    private fun loadList() {
        showLoading()
        postList = ArrayList()
        ApiClient.apiSevice.getAllPosts().enqueue(object :Callback<ArrayList<Post>>{
            override fun onResponse(
                call: Call<ArrayList<Post>>,
                response: Response<ArrayList<Post>>
            ) {
                if (response.isSuccessful){
                   val list = response.body()!!
                    postList=list
                    myAdapter.submitList(postList)
                }
                hideLoading()
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                hideLoading()

            }
        })
    }
    fun showLoading(){
        loadingProgressBar.visibility=View.VISIBLE
    }
    fun hideLoading(){
        loadingProgressBar.visibility=View.GONE
    }
}