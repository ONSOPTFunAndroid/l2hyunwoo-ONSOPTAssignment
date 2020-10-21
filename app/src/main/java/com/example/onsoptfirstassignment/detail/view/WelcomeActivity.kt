package com.example.onsoptfirstassignment.detail.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.onsoptfirstassignment.databinding.ActivityWelcomeBinding
import com.example.onsoptfirstassignment.detail.viewmodel.WelcomeViewModel

class WelcomeActivity : AppCompatActivity() {
    private val welcomeViewModel: WelcomeViewModel by viewModels()
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.welcomeViewModel = welcomeViewModel
        binding.lifecycleOwner = this
    }
}