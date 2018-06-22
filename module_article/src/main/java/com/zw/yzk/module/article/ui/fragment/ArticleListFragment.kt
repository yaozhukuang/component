package com.zw.yzk.module.article.ui.fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import com.zw.yzk.component.banner.Banner
import com.zw.yzk.component.banner.DefaultIndicator
import com.zw.yzk.component.common.base.BasePresenterFragment
import com.zw.yzk.component.common.base.BaseWebActivity
import com.zw.yzk.component.common.image.ImageLoader
import com.zw.yzk.component.common.utils.IntentUtils
import com.zw.yzk.component.common.utils.LogUtils
import com.zw.yzk.component.compiler.ModuleManager
import com.zw.yzk.module.article.R
import com.zw.yzk.module.article.data.Article
import com.zw.yzk.module.article.data.BannerEntity
import com.zw.yzk.module.article.presenter.ArticleListPresenter
import com.zw.yzk.module.article.ui.adapter.ArticleAdapter
import com.zw.yzk.module.article.ui.view.ArticleListView
import kotlinx.android.synthetic.main.module_article_fragment_article.*
import timber.log.Timber


/**
 * @author zhanwei
 */
class ArticleListFragment : BasePresenterFragment<ArticleListPresenter>(), ArticleListView {

    private var list: RecyclerView? = null
    private var refreshLayout: SwipeRefreshLayout? = null
    private val articleAdapter = ArticleAdapter()

    override fun getLayoutId() = R.layout.module_article_fragment_article

    override fun initPresenter() {
        presenter = ArticleListPresenter()
        presenter?.setView(this)
    }

    override fun init() {
        setListener()
        initRecyclerView()
        articleAdapter.isRefreshing = true
        getBanner()
        getArticleList(0)
    }

    override fun setArticleList(articleList: List<Article>) {
        if (articleAdapter.isRefreshing) {
            articleAdapter.setData(articleList)
            articleAdapter.isRefreshing = false
        } else {
            articleAdapter.add(articleList)
            if (articleList.isEmpty()) {
                articleAdapter.loadAll()
            } else {
                articleAdapter.loadSucceed()
            }
        }
    }

    override fun getArticleListFailed() {
        articleAdapter.finishFailed()
    }

    override fun setBannerList(list: List<BannerEntity>) {
        (articleAdapter.header as Banner).startBanner(list)
    }

    /**
     * 初始化RecyclerView
     */
    private fun initRecyclerView() {
        list = rootView?.findViewById(R.id.list) as RecyclerView
        refreshLayout = rootView?.findViewById(R.id.refreshLayout) as SwipeRefreshLayout

        list?.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        articleAdapter.with(list)
                .swipeRefreshLayout(refreshLayout)
                .header(createBanner())
                .setRefreshListener { getArticleList(0) }
                .setLoadMoreListener { getArticleList(articleAdapter.dataCount) }
                .empty(LayoutInflater.from(activity).inflate(R.layout.component_res_layout_empty_data, list, false))
                .build()
    }

    /**
     * 设置搜索按钮点击事件
     */
    private fun setListener() {
        searchBtn.setOnClickListener {
            if(ModuleManager.getInstance().xModuleSearch != null) {
                ModuleManager.getInstance().xModuleSearch.startSearchActivity(activity!!)
            }
        }
    }

    /**
     * 创建banner
     */
    private fun createBanner(): Banner {
        val banner = LayoutInflater.from(activity).inflate(R.layout.module_article_layout_banner, list, false) as Banner
        banner.setIndicator(DefaultIndicator())
                .setImageLoader { item, image, _ ->
                    ImageLoader.load(activity!!, (item as BannerEntity).path, image)
                    image.setOnClickListener {
                        IntentUtils.startBroswer(activity!!, item.url)
                    }
                }.build()

        return banner
    }

    /**
     * 获取文章数据
     */
    private fun getArticleList(index: Int) {
        presenter?.getArticleList(index + 1)
    }

    /**
     * 获取轮播图
     */
    private fun getBanner() {
        presenter?.getBannerList()
    }

}