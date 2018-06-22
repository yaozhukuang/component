package com.zw.yzk.module.account.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.zw.yzk.component.common.base.BasePresenterActivity
import com.zw.yzk.component.common.utils.ToolbarBuilder
import com.zw.yzk.module.account.R
import com.zw.yzk.module.account.data.NavigationGroup
import com.zw.yzk.module.account.presenter.NavigationPresenter
import com.zw.yzk.module.account.ui.adapter.NavigationAdapter
import com.zw.yzk.module.account.ui.view.NavigationView
import kotlinx.android.synthetic.main.module_account_activity_navigation.*

class NavigationActivity : BasePresenterActivity<NavigationPresenter>(), NavigationView {

    private val adapter = NavigationAdapter()

    override fun getLayoutId() = R.layout.module_account_activity_navigation

    override fun initPresenter() {
        presenter = NavigationPresenter(this)
    }

    override fun initToolbar() {
        ToolbarBuilder.builder(this).setTitle(R.string.module_account_navigation).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        presenter?.getNavigation(this)
    }

    override fun setNavigationList(list: List<NavigationGroup>) {
        adapter.setData(list)
    }

    private fun initRecyclerView() {
        webNavigation.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter.with(webNavigation).build()
    }


}