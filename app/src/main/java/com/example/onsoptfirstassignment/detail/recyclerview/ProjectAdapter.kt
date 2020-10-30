package com.example.onsoptfirstassignment.detail.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onsoptfirstassignment.R
import com.example.onsoptfirstassignment.detail.model.ProjectData
import java.util.*

class ProjectAdapter(private val context : Context) : RecyclerView.Adapter<ProjectViewHolder>(), ItemTouchHelperAdapter {

    var datas = mutableListOf<ProjectData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_project, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.onBindViewHolder(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    override fun onItemMove(from: Int, to: Int): Boolean {
        if(from < to) {
            for(i in from until to) {
                Collections.swap(datas, i, i+1)
            }
        } else {
            for(i in from downTo (to+1)) {
                Collections.swap(datas, i, i-1)
            }
        }
        notifyItemMoved(from, to)
        return true
    }

    override fun onItemDismiss(position: Int) {
        datas.removeAt(position)
        notifyItemRemoved(position)
    }

    fun changeLayout(isLinearLayout: Boolean) {

    }

}