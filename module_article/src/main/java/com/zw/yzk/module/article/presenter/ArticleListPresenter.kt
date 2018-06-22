package com.zw.yzk.module.article.presenter

import com.zw.yzk.component.common.base.BaseObserver
import com.zw.yzk.component.common.base.BaseTask
import com.zw.yzk.component.common.presenter.Presenter
import com.zw.yzk.component.common.utils.LogUtils
import com.zw.yzk.component.network.usecase.DefaultUseCase
import com.zw.yzk.module.article.data.ArticleResponse
import com.zw.yzk.module.article.data.ArticleTask
import com.zw.yzk.module.article.data.BannerResponse
import com.zw.yzk.module.article.repository.ArticleRepository
import com.zw.yzk.module.article.repository.BannerRepository
import com.zw.yzk.module.article.ui.view.ArticleListView

/**
 * @author zhanwei
 */
class ArticleListPresenter : Presenter {

    private var uArticle: DefaultUseCase<ArticleTask>? = null
    private var uBanner: DefaultUseCase<BaseTask>? = null
    private var articleView: ArticleListView? = null

    override fun onCreate() {
        uArticle = DefaultUseCase(ArticleRepository())
        uBanner = DefaultUseCase(BannerRepository())
    }

    override fun onDestroy() {
        uArticle?.dispose()
        uBanner?.dispose()
    }

    fun setView(view: ArticleListView) {
        this.articleView = view
    }

    /**
     * 请求轮播图
     */
    fun getBannerList() {
        if (articleView == null) {
            LogUtils.e("ArticleListView is null in ArticleListPresenter")
            return
        }
        uBanner?.execute(BannerObserver(articleView!!), BaseTask())
    }

    /**
     * 请求文章列表
     */
    fun getArticleList(index: Int) {
        if (articleView == null) {
            LogUtils.e("ArticleListView is null in ArticleListPresenter")
            return
        }
        uArticle?.execute(ArticleObserver(articleView!!), ArticleTask(index))
    }

    private class ArticleObserver(val articleView: ArticleListView)
        : BaseObserver<ArticleResponse>() {

        override fun onNext(t: ArticleResponse) {
            super.onNext(t)
            t.entity.list?.apply {
                articleView.setArticleList(this)
            }
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)
            articleView.getArticleListFailed()
        }

    }

    private class BannerObserver(val articleView: ArticleListView) : BaseObserver<BannerResponse>() {
        override fun onNext(t: BannerResponse) {
            super.onNext(t)
            articleView.setBannerList(t.list)
        }
    }

}