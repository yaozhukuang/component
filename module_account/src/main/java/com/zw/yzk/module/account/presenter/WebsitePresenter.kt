package com.zw.yzk.module.account.presenter

import android.support.v7.app.AppCompatActivity
import com.zw.yzk.component.common.base.BaseDialogObserver
import com.zw.yzk.component.common.base.BaseTask
import com.zw.yzk.component.common.presenter.Presenter
import com.zw.yzk.component.network.usecase.DefaultUseCase
import com.zw.yzk.module.account.data.WebSiteResponse
import com.zw.yzk.module.account.repository.WebsiteRepository
import com.zw.yzk.module.account.ui.view.WebsiteView

class WebsitePresenter : Presenter {

    private var view: WebsiteView? = null
    private var uWebsite: DefaultUseCase<BaseTask>? = null

    override fun onCreate() {
        uWebsite = DefaultUseCase(WebsiteRepository())
    }

    override fun onDestroy() {
        uWebsite?.dispose()
    }

    fun setView(view: WebsiteView) {
        this.view = view
    }

    /**
     * 获取文章列表
     */
    fun getWebsiteList(activity: AppCompatActivity) {
        uWebsite?.execute(WebsiteObserver(view!!, activity), BaseTask())
    }

    private class WebsiteObserver(val view: WebsiteView, activity: AppCompatActivity)
        : BaseDialogObserver<WebSiteResponse>(activity) {

        override fun onNext(t: WebSiteResponse) {
            super.onNext(t)
            view.setWebsiteList(t.list)
        }

    }
}