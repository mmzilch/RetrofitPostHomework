package com.example.retrofitposthomework.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitposthomework.R
import com.example.retrofitposthomework.model.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter (var postList:ArrayList<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>(){

    var mClickListener:ClickListener? = null

    fun setClickListener(clickListener: ClickListener) {
        this.mClickListener = clickListener
    }

    inner class PostViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView),View.OnClickListener{

        private lateinit var post:Post

        init {
            itemView.setOnClickListener(this)//initialize onClick fun:s and/ when start create obj,it works
        }

        fun bindVoting(post: Post) {
            this.post=post
            itemView.tvTitle.text = post.title
        }

        override fun onClick(v: View?) {
            mClickListener?.onClick(post)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindVoting(postList[position])
    }

    interface ClickListener {
        fun onClick(post:Post)
    }

}