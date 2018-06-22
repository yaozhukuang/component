package com.zw.yzk.component.common.module

import android.content.Context
import android.support.v4.app.Fragment

/**
 * @author zhanwei
 * module_account 模块对外暴露功能的接口
 */
interface AccountModule: MService {

    /**
     * 返回我的Fragment
     * @return 我的Fragment
     */
    fun getMyAccountFragment(): Fragment

    /**
     * 打开登录页面
     */
    fun startLoginActivity(context: Context)
}