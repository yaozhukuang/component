package com.zw.yzk.module.project

import android.support.v4.app.Fragment
import com.zw.yzk.component.annotation.XModule
import com.zw.yzk.component.common.constant.MODULE_PROJECT
import com.zw.yzk.component.common.module.ProjectModule
import com.zw.yzk.module.project.ui.fragment.ProjectTreeFragment

@XModule(MODULE_PROJECT)
class ProjectModuleImpl: ProjectModule {
    override fun getProjectTreeFragment(): Fragment {
        return ProjectTreeFragment()
    }
}