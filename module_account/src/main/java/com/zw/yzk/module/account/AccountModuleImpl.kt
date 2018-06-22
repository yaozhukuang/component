package com.zw.yzk.module.account

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.zw.yzk.component.annotation.XModule
import com.zw.yzk.component.common.constant.MODULE_ACCOUNT

import com.zw.yzk.component.common.module.AccountModule
import com.zw.yzk.module.account.ui.activity.LoginActivity
import com.zw.yzk.module.account.ui.fragment.AccountFragment

/**
 * @author zhanwei
 */
@XModule(MODULE_ACCOUNT)
class AccountModuleImpl : AccountModule {

    override fun startLoginActivity(context: Context) {
        context.startActivity(Intent(context, LoginActivity::class.java))
    }

    override fun getMyAccountFragment(): Fragment = AccountFragment()
}