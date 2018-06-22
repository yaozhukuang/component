package com.zw.yzk.component

import com.zw.yzk.component.annotation.XModuleInit
import com.zw.yzk.component.common.base.BaseApplication
import com.zw.yzk.component.compiler.ModuleInit

/**
 * @author zhanwei
 */
@XModuleInit("ModuleInit")
class MyApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        ModuleInit.init()
    }
}