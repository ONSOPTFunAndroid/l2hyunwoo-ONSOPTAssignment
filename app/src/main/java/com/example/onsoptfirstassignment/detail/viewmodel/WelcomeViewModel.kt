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
    val _projectList = MutableLiveData<List<ProjectData>>()
    val projectList : LiveData<List<ProjectData>>
        get() = _projectList

    init {
        setText()
        _projectList.postValue(initProjectList())
    }

    private fun setText() {
        val myId = LoginPreference.myId
        val setWelcomeText = myId + "님\n어서오세요"
        welcomeText.set(setWelcomeText)
    }
}