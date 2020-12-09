package com.example.onsoptfirstassignment.detail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.onsoptfirstassignment.R
import com.example.onsoptfirstassignment.databinding.FragmentSearchBinding
import com.example.onsoptfirstassignment.detail.adapter.MainViewPagerAdapter
import com.example.onsoptfirstassignment.detail.adapter.SearchViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        val mainViewPagerAdapter = SearchViewPagerAdapter(childFragmentManager)
        binding.vpSearchSlider.adapter = mainViewPagerAdapter
        binding.tabSearch.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.vpSearchSlider.setCurrentItem(tab?.position!!)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        return binding.root
    }
}