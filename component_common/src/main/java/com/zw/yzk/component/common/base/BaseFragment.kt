package com.zw.yzk.component.common.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 所有fragment基类
 */
abstract class BaseFragment : Fragment() {

    protected var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(getLayoutId(), container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    /**
     * 获取布局文件ID
     * @return Activity布局文件
     */
    protected abstract fun getLayoutId(): Int

    /**
     * 创建导航栏
     */
    protected abstract fun init()
}