package com.zw.yzk.module.project.ui.view

import com.zw.yzk.module.project.data.ProjectEntity

interface ProjectTreeView {
    fun setProjectTree(list: List<ProjectEntity>)
    fun getProjectTreeFailed()
}