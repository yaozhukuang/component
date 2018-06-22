package com.zw.yzk.module.project.ui.fragment

import android.os.Handler
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.zw.yzk.component.common.base.BasePresenterFragment
import com.zw.yzk.module.project.R
import com.zw.yzk.module.project.com.zw.yzk.module.project.ui.adapter.ProjectTreeItemDecoration
import com.zw.yzk.module.project.data.ProjectEntity
import com.zw.yzk.module.project.presenter.ProjectTreePresenter
import com.zw.yzk.module.project.ui.adapter.ProjectTreeAdapter
import com.zw.yzk.module.project.ui.view.ProjectTreeView

class ProjectTreeFragment : BasePresenterFragment<ProjectTreePresenter>(), ProjectTreeView {

    private var list: RecyclerView? = null
    private var adapter = ProjectTreeAdapter()

    override fun getLayoutId() = R.layout.module_project_fragment_project_tree

    override fun initPresenter() {
        presenter = ProjectTreePresenter()
        presenter?.setView(this)
    }

    override fun init() {
        initRecyclerView()
        Handler().post {
            presenter?.getProjectTree(activity!!)
        }
    }

    override fun setProjectTree(list: List<ProjectEntity>) {
        adapter.setData(list)
    }

    override fun getProjectTreeFailed() {

    }

    private fun initRecyclerView() {
        list = rootView?.findViewById(R.id.list) as RecyclerView
        list?.apply {
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
            val width = context.resources.displayMetrics.density * 16
            addItemDecoration(ProjectTreeItemDecoration(width.toInt()))
        }

        adapter.with(list)
                .header(LayoutInflater.from(activity).inflate(R.layout.module_project_layout_project_tree_header, list, false))
                .empty(LayoutInflater.from(activity).inflate(R.layout.component_res_layout_empty_data, list, false))
                .build()
    }
}