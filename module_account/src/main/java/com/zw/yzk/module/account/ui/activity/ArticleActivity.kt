package com.zw.yzk.module.account.ui.activity

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import com.zw.yzk.component.common.base.BasePresenterActivity
import com.zw.yzk.component.common.utils.ToolbarBuilder
import com.zw.yzk.module.account.R
import com.zw.yzk.module.account.data.Article
import com.zw.yzk.module.account.presenter.ArticlePresenter
import com.zw.yzk.module.account.ui.adapter.ArticleAdapter
import com.zw.yzk.module.account.ui.view.ArticleView
import kotlinx.android.synthetic.main.module_account_activity_article.*

class ArticleActivity : BasePresenterActivity<ArticlePresenter>(), ArticleView {

    private val adapter = ArticleAdapter()

    override fun getLayoutId() = R.layout.module_account_activity_article

    override fun initPresenter() {
        presenter = ArticlePresenter()
        presenter?.setView(this)
    }

    override fun initToolbar() {
        ToolbarBuilder.builder(this).setTitle(R.string.module_account_login).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()
        adapter.isRefreshing = true
        getArticleList(0)

    }

    override fun setArticleList(list: List<Article>) {
        if (adapter.isRefreshing) {
            adapter.setData(list)
            adapter.isRefreshing = false
        } else {
            if (list.isEmpty()) {
                adapter.loadAll()
            } else {
                adapter.add(list)
                adapter.loadSucceed()
            }
        }
    }

    override fun getArticleListFailed() {
        adapter.finishFailed()
    }

    private fun initRecyclerView() {
        articleList.apply {
            layoutManager = LinearLayoutManager(this@ArticleActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@ArticleActivity, DividerItemDecoration.VERTICAL))
        }
        adapter.with(articleList)
                .swipeRefreshLayout(refreshLayout)
                .empty(LayoutInflater.from(this).inflate(R.layout.component_res_layout_empty_data, articleList, false))
                .setRefreshListener {
                    getArticleList(0) }
                .setLoadMoreListener {
                    getArticleList(adapter.dataCount) }
                .build()
    }

    private fun getArticleList(index: Int) {
        presenter?.getArticleList(index)
    }
}