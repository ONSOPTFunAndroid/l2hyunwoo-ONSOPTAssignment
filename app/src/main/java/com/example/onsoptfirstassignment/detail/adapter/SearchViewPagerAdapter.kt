package com.example.onsoptfirstassignment.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.onsoptfirstassignment.detail.view.SearchBookFragment
import com.example.onsoptfirstassignment.detail.view.SearchDocumentFragment

class SearchViewPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount() = 2
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> SearchBookFragment()
            else -> SearchDocumentFragment()
        }
    }
}