package com.example.onsoptfirstassignment.detail.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onsoptfirstassignment.R
import com.example.onsoptfirstassignment.detail.model.ProjectData
import com.example.onsoptfirstassignment.detail.model.initProjectList
import com.example.onsoptfirstassignment.preference.LoginPreference

class WelcomeViewModel : ViewModel() {

    val welcomeText = ObservableField<String>()
    private val _projectList = MutableLiveData<MutableList<ProjectData>>()
    val projectList : LiveData<MutableList<ProjectData>>
        get() = _projectList
    private val _floatingButtonClickListener = MutableLiveData<Boolean>()
    val floatingButtonClickListener : LiveData<Boolean>
        get() = _floatingButtonClickListener
    private val _isLinearLayout = MutableLiveData<Boolean>()
    val isLinearLayout : LiveData<Boolean>
        get() = _isLinearLayout

    init {
        setText()
        _projectList.postValue(initProjectList())
        _floatingButtonClickListener.value = false
        _isLinearLayout.value = true
    }

    private fun setText() {
        val myId = LoginPreference.myId
        val setWelcomeText = myId + "님\n어서오세요"
        welcomeText.set(setWelcomeText)
    }

    fun floatingButtonClicked() {
        _floatingButtonClickListener.value = true
    }

    fun setFloatingButtonClickEventFalse() {
        _floatingButtonClickListener.value = false
    }

    fun linearToGrid() {
        _isLinearLayout.value = false
    }

    fun gridToLinear() {
        _isLinearLayout.value = true
    }
}