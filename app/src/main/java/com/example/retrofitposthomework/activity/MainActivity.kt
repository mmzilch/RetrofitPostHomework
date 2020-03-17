package com.example.retrofitposthomework.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitposthomework.R
import com.example.retrofitposthomework.adapter.PostAdapter
import com.example.retrofitposthomework.api.PostApiInterface
import com.example.retrofitposthomework.model.Post
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity: AppCompatActivity(), PostAdapter.ClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPost()

    }

    fun loadUserList(postList: List<Post>) {
        rcyPost.apply {
            val postAdapter = PostAdapter(postList as ArrayList<Post>)
            layoutManager = LinearLayoutManager(context)
            postAdapter.setClickListener(this@MainActivity)
            adapter = postAdapter
        }
    }

    fun getPost() {
        var BASE_URL = "https://jsonplaceholder.typicode.com/"
        var retrofitPost = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var retrofitService = retrofitPost.create(PostApiInterface::class.java)
        var apiCall = retrofitService.getPost()

        apiCall.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("Failure>>>>>>", t.toString())

            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                var postList = response.body()//to get all json code (body)
                Log.d("Response>>>>>>", postList.toString())
                loadUserList(postList as ArrayList<Post>)
            }
        })
    }

    override fun onClick(post: Post) {
        Toast.makeText(applicationContext, "${post.title}", Toast.LENGTH_LONG).show()

        val intent =
            Intent(this@MainActivity, NextActivity::class.java)
        intent.putExtra("title",post.title)
        startActivity(intent)
    }
}
