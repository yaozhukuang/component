package com.zw.yzk.module.project.ui.view

import com.zw.yzk.module.project.data.Project

interface ProjectListView {
    fun setProjectList(list: List<Project>)
    fun getProjectListFailed()
}