package com.example.onsoptfirstassignment.detail.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.onsoptfirstassignment.R
import com.example.onsoptfirstassignment.databinding.ActivityWelcomeBinding
import com.example.onsoptfirstassignment.detail.adapter.MainViewPagerAdapter
import com.example.onsoptfirstassignment.detail.model.ProjectData
import com.example.onsoptfirstassignment.detail.recyclerview.BaseRecyclerView
import com.example.onsoptfirstassignment.detail.recyclerview.ItemTouchHelperCallback
import com.example.onsoptfirstassignment.detail.recyclerview.OnStartDragListener
import com.example.onsoptfirstassignment.detail.recyclerview.ProjectAdapter
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

        val mainViewPagerAdapter = MainViewPagerAdapter(this)
        binding.vpFragmentSlider.apply {
            adapter = mainViewPagerAdapter
            registerOnPageChangeCallback(PageChangeCallBack())
        }

        binding.welcomeBottomnav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.main_project -> binding.vpFragmentSlider.currentItem = 0
                R.id.main_search -> binding.vpFragmentSlider.currentItem = 1
                R.id.main_proile -> binding.vpFragmentSlider.currentItem = 2
            }
            true
        }
    }

    private inner class PageChangeCallBack : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.welcomeBottomnav.selectedItemId = when (position) {
                0 -> R.id.main_project
                1 -> R.id.main_search
                2 -> R.id.main_proile
                else -> throw IllegalAccessException("No Such Position")
            }
        }
    }
}
