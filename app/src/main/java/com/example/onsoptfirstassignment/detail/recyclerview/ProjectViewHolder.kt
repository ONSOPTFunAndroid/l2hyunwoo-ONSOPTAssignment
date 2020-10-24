package com.example.onsoptfirstassignment.detail.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onsoptfirstassignment.R
import com.example.onsoptfirstassignment.detail.model.ProjectData

class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val img_portfolio = itemView.findViewById<ImageView>(R.id.img_project)
    val txt_title = itemView.findViewById<TextView>(R.id.txt_title)
    val txt_explain = itemView.findViewById<TextView>(R.id.txt_explain)

    fun onBindViewHolder(projectData: ProjectData) {
        img_portfolio.setImageResource(projectData.img_portfolio)
        txt_title.text = projectData.txt_title
        txt_explain.text = projectData.txt_explain
    }
}