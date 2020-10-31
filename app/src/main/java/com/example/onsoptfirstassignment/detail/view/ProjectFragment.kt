package com.example.onsoptfirstassignment.detail.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onsoptfirstassignment.R
import com.example.onsoptfirstassignment.databinding.FragmentProjectBinding
import com.example.onsoptfirstassignment.detail.model.ProjectData
import com.example.onsoptfirstassignment.detail.recyclerview.ItemTouchHelperCallback
import com.example.onsoptfirstassignment.detail.recyclerview.OnStartDragListener
import com.example.onsoptfirstassignment.detail.recyclerview.ProjectAdapter
import com.example.onsoptfirstassignment.detail.viewmodel.WelcomeViewModel

class ProjectFragment : Fragment(), OnStartDragListener {
    private val welcomeViewModel : WelcomeViewModel by activityViewModels()
    private lateinit var projectBinding: FragmentProjectBinding
    private lateinit var mAdapter: ProjectAdapter
    private lateinit var touchHelper: ItemTouchHelper
    var data = mutableListOf<ProjectData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        projectBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_project, container, false)
        projectBinding.welcomeViewModel = welcomeViewModel
        projectBinding.lifecycleOwner = viewLifecycleOwner
        return projectBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = ProjectAdapter(projectBinding.root.context, ProjectAdapter.LINEAR)
        addData()
        projectBinding.recyclerviewProject.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
            mAdapter.datas = data
            mAdapter.notifyDataSetChanged()
            setHasFixedSize(true)
        }
        attachItemTouchHelper(mAdapter)
        projectBinding.welcomeViewModel!!.floatingButtonClickListener.observe(viewLifecycleOwner, {
            if (it) {
                if (projectBinding.recyclerviewProject.layoutManager is GridLayoutManager) {
                    Log.d("Welcome", "here2")
                    mAdapter.datas.clear()
                    mAdapter.setLinearLayout()
                    mAdapter.datas = data
                    addData()
                    projectBinding.recyclerviewProject.layoutManager =
                        LinearLayoutManager(projectBinding.root.context)
                    projectBinding.recyclerviewProject.adapter = mAdapter
                    mAdapter.notifyDataSetChanged()
                } else if (projectBinding.recyclerviewProject.layoutManager is LinearLayoutManager) {
                    Log.d("Welcome", "here1")
                    mAdapter.datas.clear()
                    mAdapter.setGridLayout()
                    mAdapter.datas = data
                    addData()
                    projectBinding.recyclerviewProject.layoutManager =
                        GridLayoutManager(projectBinding.root.context, 2)
                    projectBinding.recyclerviewProject.adapter = mAdapter
                    mAdapter.notifyDataSetChanged()
                }
                "레이아웃 변경".toast()
                welcomeViewModel.setFloatingButtonClickEventFalse()
            }
        })
    }

    private fun attachItemTouchHelper(adapter: ProjectAdapter) {
        val callback = ItemTouchHelperCallback(projectBinding.root.context, adapter)
        touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(projectBinding.recyclerviewProject)
    }

    private fun addData() {
        data.apply {
            add(
                ProjectData(
                    img_portfolio = R.drawable.ounce_logo,
                    txt_title = "OUNCE",
                    txt_explain = "온스(OUNCE)는 제가 참여했던 26기 SOPT APP-JAM에서 " +
                            "제작한 어플리케이션입니다. 저는 민구형과 주예랑 함께 안드로이드 " +
                            "개발 파트에서 작업을 했습니다."
                )
            )
            add(
                ProjectData(
                    img_portfolio = R.drawable.maru_logo,
                    txt_title = "마루",
                    txt_explain = "마루는 OUNCE의 구성원로 구성된 사이드 프로젝트입니다. " +
                            "온라인 독서토론 어플리케이션으로 저는 안드로이드 개발 및 기획에 " +
                            "참여했습니다."
                )
            )
            add(
                ProjectData(
                    img_portfolio = R.drawable.sopt_spring,
                    txt_title = "충무로 스프링 스터디",
                    txt_explain = "SOPT에 들어와서 참여하게 된 첫 스터디로 " +
                            "동관/경선/혜선/민지와 함께 즐거운 스프링 공부를 했습니다. " +
                            "SOPT 선정 스터디 1위도 했었습니다."
                )

            )
            add(
                ProjectData(
                    img_portfolio = R.drawable.android_siba,
                    txt_title = "안드로이드 심화 스터디 : SIBA",
                    txt_explain = "안드로이드를 더 깊이 공부하기 위한 4인방 " +
                            "김영민/송훈기/이현우/박진수의 환장 콜라보 " +
                            "매일매일 모각공 및 자료 공유 활성화"
                )
            )
            add(
                ProjectData(
                    img_portfolio = R.drawable.kotlin,
                    txt_title = "안드로이드 Kotlin 스터디",
                    txt_explain = "처음 안드로이드를 접한 OB들의 초보 탈출기 " +
                            "3주간의 인텐시브 트레이닝으로 코틀린 격파"
                )
            )
            add(
                ProjectData(
                    img_portfolio = R.drawable.kotlin,
                    txt_title = "안드로이드 Kotlin 스터디",
                    txt_explain = "처음 안드로이드를 접한 OB들의 초보 탈출기 " +
                            "3주간의 인텐시브 트레이닝으로 코틀린 격파"
                )
            )
            add(
                ProjectData(
                    img_portfolio = R.drawable.kotlin,
                    txt_title = "안드로이드 Kotlin 스터디",
                    txt_explain = "처음 안드로이드를 접한 OB들의 초보 탈출기 " +
                            "3주간의 인텐시브 트레이닝으로 코틀린 격파"
                )
            )
            add(
                ProjectData(
                    img_portfolio = R.drawable.kotlin,
                    txt_title = "안드로이드 Kotlin 스터디",
                    txt_explain = "처음 안드로이드를 접한 OB들의 초보 탈출기 " +
                            "3주간의 인텐시브 트레이닝으로 코틀린 격파"
                )
            )
            add(
                ProjectData(
                    img_portfolio = R.drawable.kotlin,
                    txt_title = "안드로이드 Kotlin 스터디",
                    txt_explain = "처음 안드로이드를 접한 OB들의 초보 탈출기 " +
                            "3주간의 인텐시브 트레이닝으로 코틀린 격파"
                )
            )
            add(
                ProjectData(
                    img_portfolio = R.drawable.kotlin,
                    txt_title = "안드로이드 Kotlin 스터디",
                    txt_explain = "처음 안드로이드를 접한 OB들의 초보 탈출기 " +
                            "3주간의 인텐시브 트레이닝으로 코틀린 격파"
                )
            )
            add(
                ProjectData(
                    img_portfolio = R.drawable.kotlin,
                    txt_title = "안드로이드 Kotlin 스터디",
                    txt_explain = "처음 안드로이드를 접한 OB들의 초보 탈출기 " +
                            "3주간의 인텐시브 트레이닝으로 코틀린 격파"
                )
            )
            add(
                ProjectData(
                    img_portfolio = R.drawable.kotlin,
                    txt_title = "안드로이드 Kotlin 스터디",
                    txt_explain = "처음 안드로이드를 접한 OB들의 초보 탈출기 " +
                            "3주간의 인텐시브 트레이닝으로 코틀린 격파"
                )
            )
            add(
                ProjectData(
                    img_portfolio = R.drawable.kotlin,
                    txt_title = "안드로이드 Kotlin 스터디",
                    txt_explain = "처음 안드로이드를 접한 OB들의 초보 탈출기 " +
                            "3주간의 인텐시브 트레이닝으로 코틀린 격파"
                )
            )
            add(
                ProjectData(
                    img_portfolio = R.drawable.kotlin,
                    txt_title = "안드로이드 Kotlin 스터디",
                    txt_explain = "처음 안드로이드를 접한 OB들의 초보 탈출기 " +
                            "3주간의 인텐시브 트레이닝으로 코틀린 격파"
                )
            )
            add(
                ProjectData(
                    img_portfolio = R.drawable.kotlin,
                    txt_title = "안드로이드 Kotlin 스터디",
                    txt_explain = "처음 안드로이드를 접한 OB들의 초보 탈출기 " +
                            "3주간의 인텐시브 트레이닝으로 코틀린 격파"
                )
            )
            add(
                ProjectData(
                    img_portfolio = R.drawable.kotlin,
                    txt_title = "안드로이드 Kotlin 스터디",
                    txt_explain = "처음 안드로이드를 접한 OB들의 초보 탈출기 " +
                            "3주간의 인텐시브 트레이닝으로 코틀린 격파"
                )
            )
        }
    }

    private fun String.toast() {
        Toast.makeText(projectBinding.root.context, this, Toast.LENGTH_SHORT).show()
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        touchHelper.startDrag(viewHolder)
    }
}