package com.example.onsoptfirstassignment.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.onsoptfirstassignment.detail.view.ProfileFragment
import com.example.onsoptfirstassignment.detail.view.ProjectFragment
import com.example.onsoptfirstassignment.detail.view.SearchFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> ProjectFragment()
            1 -> SearchFragment()
            else -> ProfileFragment()
        }
    }
}