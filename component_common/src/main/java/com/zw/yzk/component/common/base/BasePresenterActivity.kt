package com.zw.yzk.component.common.base

import android.os.Bundle
import com.zw.yzk.component.common.presenter.Presenter

/**
 * 所有带presenter基类，继承于BaseActivity
 */
abstract class BasePresenterActivity<P : Presenter> : BaseActivity() {

    protected var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化presenter
        initPresenter()
        presenter?.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        //注销Presenter
        presenter?.onDestroy()
    }

    /**
     * 初始化presenter
     */
    protected abstract fun initPresenter()
}