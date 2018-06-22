package com.zw.yzk.module.account.presenter

import com.zw.yzk.component.common.base.BaseObserver
import com.zw.yzk.component.common.presenter.Presenter
import com.zw.yzk.component.network.usecase.DefaultUseCase
import com.zw.yzk.module.account.data.ArticleResponse
import com.zw.yzk.module.account.data.ArticleTask
import com.zw.yzk.module.account.repository.ArticleRepository
import com.zw.yzk.module.account.ui.view.ArticleView

class ArticlePresenter : Presenter {

    private var view: ArticleView? = null
    private var uLogin: DefaultUseCase<ArticleTask>? = null

    override fun onCreate() {
        uLogin = DefaultUseCase(ArticleRepository())
    }

    override fun onDestroy() {
        uLogin?.dispose()
    }

    fun setView(view: ArticleView) {
        this.view = view
    }

    /**
     * 获取文章列表
     */
    fun getArticleList(index: Int) {
        uLogin?.execute(ArticleObserver(view!!), ArticleTask(index))
    }

    private class ArticleObserver(val view: ArticleView) : BaseObserver<ArticleResponse>() {

        override fun onNext(t: ArticleResponse) {
            super.onNext(t)
            view.setArticleList(t.entity.list)
        }

        override fun onError(e: Throwable?) {
            super.onError(e)
            view.getArticleListFailed()
        }
    }
}