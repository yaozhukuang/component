package com.zw.yzk.component.common.module

import android.content.Context

/**
 * @author zhanwei
 * module_search 模块对外暴露功能的接口
 */
interface SearchModule: MService {

    /**
     * 打开搜索页面
     */
    fun startSearchActivity(context: Context)
}