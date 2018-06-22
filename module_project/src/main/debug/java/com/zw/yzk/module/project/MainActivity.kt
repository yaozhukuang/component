package com.zw.yzk.module.project

import android.os.Bundle
import com.zw.yzk.component.common.base.BaseActivity

class MainActivity: BaseActivity() {
    override fun getLayoutId() = R.layout.module_project_activity_main

    override fun initToolbar() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addFragment()
    }

    private fun addFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, ProjectModuleImpl().getProjectTreeFragment())
                .commit()
    }

}