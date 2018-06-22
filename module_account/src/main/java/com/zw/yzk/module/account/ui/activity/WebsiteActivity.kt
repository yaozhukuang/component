package com.zw.yzk.module.account.ui.activity

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import com.zw.yzk.component.common.base.BasePresenterActivity
import com.zw.yzk.component.common.utils.ToolbarBuilder
import com.zw.yzk.module.account.R
import com.zw.yzk.module.account.data.Website
import com.zw.yzk.module.account.presenter.WebsitePresenter
import com.zw.yzk.module.account.ui.adapter.WebsiteAdapter
import com.zw.yzk.module.account.ui.view.WebsiteView
import kotlinx.android.synthetic.main.module_account_activity_website.*

class WebsiteActivity : BasePresenterActivity<WebsitePresenter>(), WebsiteView {

    private val adapter = WebsiteAdapter()

    override fun getLayoutId() = R.layout.module_account_activity_website

    override fun initPresenter() {
        presenter = WebsitePresenter()
        presenter?.setView(this)
    }

    override fun initToolbar() {
        ToolbarBuilder.builder(this).setTitle(R.string.module_account_website).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        presenter?.getWebsiteList(this)
    }

    override fun setWebsiteList(list: List<Website>) {
        adapter.setData(list)
    }

    private fun initRecyclerView() {
        websiteList.apply {
            layoutManager = LinearLayoutManager(this@WebsiteActivity,
                    LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@WebsiteActivity, DividerItemDecoration.VERTICAL))
        }
        adapter.with(websiteList)
                .empty(LayoutInflater.from(this).inflate(R.layout.component_res_layout_empty_data, websiteList, false))
                .build()
    }

}