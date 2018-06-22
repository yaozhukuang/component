package com.zw.yzk.component.common.base

import android.app.Application
import com.zw.yzk.component.common.constant.BASE_URL
import com.zw.yzk.component.common.exception.MyEeceptionHandler
import com.zw.yzk.component.common.greendao.DaoManager
import com.zw.yzk.component.common.network.CookieConfig
import com.zw.yzk.component.common.utils.LogUtils
import com.zw.yzk.component.network.HttpManager
import com.zw.yzk.component.network.exception.ExceptionManager

/**
 * @author zhanwei
 */

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        init(true)
    }

    private fun init(debug: Boolean) {
        //初始化Log
        LogUtils.init(debug)
        //初始化Retrofit
        HttpManager.getInstance().config = CookieConfig(this, BASE_URL)
                .setDebug(debug).setLogger { LogUtils.i(it) }
        ExceptionManager.getInstance().eHandler = MyEeceptionHandler(this)
        //初始化GreenDao
        DaoManager.getInstance().init(this)
    }
}