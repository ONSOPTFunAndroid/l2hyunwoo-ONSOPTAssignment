package com.example.onsoptfirstassignment.detail.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onsoptfirstassignment.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val intent = getIntent()
        val myId = intent.getStringExtra("id") as String
        val setWelcomeText = myId + "님\n어서오세요"
        tv_welcome.text = setWelcomeText
    }
}