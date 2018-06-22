package com.zw.yzk.component

import android.content.Context
import android.content.Intent
import com.zw.yzk.component.annotation.XModule
import com.zw.yzk.component.common.constant.MODULE_APP
import com.zw.yzk.component.common.module.AppModule

@XModule(MODULE_APP)
class AppModuleImpl: AppModule {
    override fun startMainActivity(context: Context) {
        context.startActivity(Intent(context, MainActivity::class.java))
    }
}