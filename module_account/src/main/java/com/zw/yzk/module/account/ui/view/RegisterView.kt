package com.zw.yzk.module.account.ui.view

import com.zw.yzk.module.account.data.LoginEntity

interface RegisterView {
    fun registerSuccess(entity: LoginEntity)
    fun registerFailed()
}