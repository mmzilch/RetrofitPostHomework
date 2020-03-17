package com.example.retrofitposthomework.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitposthomework.R
import kotlinx.android.synthetic.main.activity_next.*

class NextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        var title=intent.getStringExtra("title")
        tvNextTitle.text=title
    }
}
