package com.zw.yzk.component.common.base

import android.os.Bundle
import com.zw.yzk.component.common.presenter.Presenter

/**
 * 所有带presenterfragment基类，继承于BaseFragment
 */
abstract class BasePresenterFragment<P: Presenter> : BaseFragment() {

    protected var presenter: P? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initPresenter()
        presenter?.onCreate()

        super.onActivityCreated(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.onDestroy()
    }


    /**
     * 初始化presenter
     */
    protected abstract fun initPresenter()
}