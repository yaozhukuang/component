package com.zw.yzk.component.common.module

import android.content.Context

/**
 * @author zhanwei
 * module_account 模块对外暴露功能的接口
 */
interface AppModule: MService {

    /**
     * 打开MainActivity
     */
    fun startMainActivity(context: Context)
}