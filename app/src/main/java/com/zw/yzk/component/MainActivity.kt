package com.zw.yzk.component

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction


import com.zw.yzk.component.common.base.BaseActivity
import com.zw.yzk.component.common.greendao.helper.UserHelper
import com.zw.yzk.component.compiler.ModuleManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var accountFragment: Fragment? = null
    private var articleFragment: Fragment? = null
    private var projectFragment: Fragment? = null

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initToolbar() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addBottomMenu()
    }

    /**
     * 添加首页底部菜单
     */
    private fun addBottomMenu() {
        bottomMenu.setTextColor(R.color.component_res_selector_tv_color_red)
                .add(getString(R.string.component_res_article), R.drawable.component_res_selector_main_article)
                .add(getString(R.string.component_res_project), R.drawable.component_res_selector_main_project)
                .add(getString(R.string.component_res_me), R.drawable.component_res_selector_main_me)
                .create()

        bottomMenu.setOnItemClickedListener { _, index ->
            when (index) {
                0 -> {
                    if (articleFragment == null) {
                        articleFragment = ModuleManager.getInstance().xModuleArticle.getArticleFragment()
                        setFragment(articleFragment, false)
                    } else {
                        setFragment(articleFragment, true)
                    }
                }
                1 -> {
                    if (projectFragment == null) {
                        projectFragment = ModuleManager.getInstance().xModuleProject.getProjectTreeFragment()
                        setFragment(projectFragment, false)
                    } else {
                        setFragment(projectFragment, true)
                    }
                }
                2 -> {
                    if(!UserHelper.checkLogin()) {
                        ModuleManager.getInstance().xModuleAccount.startLoginActivity(this)
                        bottomMenu.setChecked(0)
                        return@setOnItemClickedListener
                    }
                    if (accountFragment == null) {
                        accountFragment = ModuleManager.getInstance().xModuleAccount.getMyAccountFragment()
                        setFragment(accountFragment, false)
                    } else {
                        setFragment(accountFragment, true)
                    }
                }
            }
        }
        bottomMenu.setChecked(0)
    }

    /**
     * 切换Fragment展示
     */
    private fun setFragment(fragment: Fragment?, hasAdd: Boolean) {
        if (fragment == null) {
            return
        }
        val ft = supportFragmentManager.beginTransaction()
        hideFragment(ft)
        if (!hasAdd) {
            ft.add(R.id.container, fragment)
        }
        ft.show(fragment).commit()
    }

    /**
     * 隐藏所有的fragment
     */
    private fun hideFragment(transaction: FragmentTransaction) {
        accountFragment?.apply {
            transaction.hide(this)
        }
        articleFragment?.apply {
            transaction.hide(this)
        }
        projectFragment?.apply {
            transaction.hide(this)
        }
    }
}
