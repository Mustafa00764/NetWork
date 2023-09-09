package com.example.network.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.network.R
import com.example.network.model.Post

class PostAdapter:RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    val posts = ArrayList<Post>()

    var itemlongClick : ((Int)->Unit)? = null
    fun submitList(list: ArrayList<Post>){
        posts.clear()
        posts.addAll(list)

        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return PostViewHolder(view)
    }

    override fun getItemCount()=posts.size


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.apply {
            tvuserId.text=post.id.toString()
            tvtitly.text=post.title
            tvBody.text=post.body
            llItem.setOnLongClickListener {
                itemlongClick?.invoke(position)
                true
            }
        }
    }
    class PostViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvuserId:TextView = view.findViewById(R.id.tv_userId)
        val tvtitly:TextView = view.findViewById(R.id.tv_title)
        val tvBody:TextView = view.findViewById(R.id.tv_body)
        val llItem = view.findViewById<LinearLayout>(R.id.ll_item)
    }
}