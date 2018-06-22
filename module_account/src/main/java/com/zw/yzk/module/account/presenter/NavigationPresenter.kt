package com.zw.yzk.module.account.presenter

import android.support.v7.app.AppCompatActivity
import com.zw.yzk.component.common.base.BaseDialogObserver
import com.zw.yzk.component.common.base.BaseTask
import com.zw.yzk.component.common.presenter.Presenter
import com.zw.yzk.component.network.usecase.DefaultUseCase
import com.zw.yzk.module.account.data.NavigationResponse
import com.zw.yzk.module.account.repository.NavigationRepository
import com.zw.yzk.module.account.ui.view.NavigationView

class NavigationPresenter(val view: NavigationView) : Presenter {

    private var uNavigation: DefaultUseCase<BaseTask>? = null

    override fun onCreate() {
        uNavigation = DefaultUseCase(NavigationRepository())
    }

    override fun onDestroy() {
        uNavigation?.dispose()
    }

    fun getNavigation(activity: AppCompatActivity) {
        uNavigation?.execute(NavigationObserver(view, activity), BaseTask())
    }

    private class NavigationObserver(val view: NavigationView, activity: AppCompatActivity) :
            BaseDialogObserver<NavigationResponse>(activity) {

        override fun onNext(t: NavigationResponse) {
            super.onNext(t)
            view.setNavigationList(t.group)
        }
    }

}