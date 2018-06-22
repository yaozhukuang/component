package com.zw.yzk.module.account

import com.zw.yzk.component.annotation.ModuleInject
import com.zw.yzk.component.common.base.BaseApplication
import com.zw.yzk.component.compiler.ModuleManager


/**
 * @author zhanwei
 */
@ModuleInject
class ModuleAccountApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        ModuleManager.getInstance().xModuleApp = AppModuleImpl()
    }
}