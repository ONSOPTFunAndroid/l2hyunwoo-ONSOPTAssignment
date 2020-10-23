package com.example.onsoptfirstassignment.detail.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.onsoptfirstassignment.BR
import com.example.onsoptfirstassignment.R
import com.example.onsoptfirstassignment.databinding.ActivityWelcomeBinding
import com.example.onsoptfirstassignment.databinding.ItemProjectBinding
import com.example.onsoptfirstassignment.detail.model.ProjectData
import com.example.onsoptfirstassignment.detail.recyclerview.BaseRecyclerView
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
        binding.recyclerviewProject.adapter = object : BaseRecyclerView.BaseAdapter<ProjectData, ItemProjectBinding>(
            layoutResId = R.layout.item_project,
            bindingVariableId = BR.projectdata
        ){}
        Log.d("Welcome", "4")
    }


    companion object {
        @Suppress("UNCHECKED_CAST")
        @BindingAdapter("replaceAll")
        @JvmStatic
        fun RecyclerView.replaceAll(list : List<Any>?) {
            (this.adapter as? BaseRecyclerView.BaseAdapter<Any, *>?)?.run{
                replaceAll(list)
                notifyDataSetChanged()
            }
        }
    }
}