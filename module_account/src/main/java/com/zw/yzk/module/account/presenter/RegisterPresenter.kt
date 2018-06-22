package com.zw.yzk.module.account.presenter

import android.support.v7.app.AppCompatActivity
import com.zw.yzk.component.common.base.BaseDialogObserver
import com.zw.yzk.component.common.presenter.Presenter
import com.zw.yzk.component.network.usecase.DefaultUseCase
import com.zw.yzk.module.account.data.LoginResponse
import com.zw.yzk.module.account.data.LoginTask
import com.zw.yzk.module.account.repository.RegisterRepository
import com.zw.yzk.module.account.ui.view.RegisterView

class RegisterPresenter: Presenter {

    private var view: RegisterView? = null
    private var uLogin: DefaultUseCase<LoginTask>? = null

    override fun onCreate() {
        uLogin = DefaultUseCase(RegisterRepository())
    }

    override fun onDestroy() {
        uLogin?.dispose()
    }

     fun setView(view: RegisterView) {
        this.view = view
    }

    /**
     * 登录
     */
    fun login(activity: AppCompatActivity, account: String, password: String) {
        uLogin?.execute(RegisterObserver(view!!, activity), LoginTask(account, password))
    }

    private class RegisterObserver(val view: RegisterView, activity: AppCompatActivity)
        : BaseDialogObserver<LoginResponse>(activity) {

        override fun onNext(t: LoginResponse) {
            super.onNext(t)
            view.registerSuccess(t.entity)
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)
            view.registerFailed()
        }
    }
}