package com.zw.yzk.module.project.com.zw.yzk.module.project.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.zw.yzk.component.common.base.BasePresenterActivity
import com.zw.yzk.component.common.constant.ACTIVITY_TITLE
import com.zw.yzk.component.common.constant.PROJECT_CID
import com.zw.yzk.component.common.utils.ToolbarBuilder
import com.zw.yzk.module.project.R
import com.zw.yzk.module.project.com.zw.yzk.module.project.ui.adapter.ProjectListAdapter
import com.zw.yzk.module.project.data.Project
import com.zw.yzk.module.project.presenter.ProjectListPresenter
import com.zw.yzk.module.project.ui.view.ProjectListView
import kotlinx.android.synthetic.main.module_project_activity_project_list.*

class ProjectListActivity : BasePresenterActivity<ProjectListPresenter>(), ProjectListView {

    companion object {
        fun startProjectListActivity(context: Context, cid: Int, title: String) {
            val intent = Intent(context, ProjectListActivity::class.java)
            intent.putExtra(PROJECT_CID, cid)
            intent.putExtra(ACTIVITY_TITLE, title)
            context.startActivity(intent)
        }
    }

    private val adapter = ProjectListAdapter()

    override fun getLayoutId() = R.layout.module_project_activity_project_list

    override fun initPresenter() {
        presenter = ProjectListPresenter()
        presenter?.setView(this)
    }

    override fun initToolbar() {
        val title = intent.getStringExtra(ACTIVITY_TITLE)
                ?: getString(R.string.component_common_details)
        ToolbarBuilder.builder(this).setTitle(title).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        adapter.isRefreshing = true
        getProjectList(0)
    }

    override fun setProjectList(list: List<Project>) {
        if (adapter.isRefreshing) {
            adapter.setData(list)
            adapter.isRefreshing = false
        } else {
            adapter.add(list)
            if (list.isEmpty()) {
                adapter.loadAll()
            } else {
                adapter.loadSucceed()
            }
        }
    }

    override fun getProjectListFailed() {
        adapter.finishFailed()
    }

    /**
     * 初始化RecyclerView
     */
    private fun initRecyclerView() {
        projectList.apply {
            layoutManager = LinearLayoutManager(this@ProjectListActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@ProjectListActivity, LinearLayout.VERTICAL))
        }

        adapter.with(projectList)
                .swipeRefreshLayout(refreshLayout)
                .setRefreshListener { getProjectList(0) }
                .setLoadMoreListener { getProjectList(adapter.dataCount) }
                .empty(LayoutInflater.from(this).inflate(R.layout.component_res_layout_empty_data, projectList, false))
                .build()
    }

    /**
     * 获取项目列表
     */
    private fun getProjectList(index: Int) {
        presenter?.getProjectTree(intent.getIntExtra(PROJECT_CID, 0), index + 1)
    }
}