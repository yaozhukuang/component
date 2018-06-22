package com.zw.yzk.module.account.ui.view

import com.zw.yzk.module.account.data.LoginEntity

interface LoginView {
    fun loginSuccess(entity: LoginEntity)
    fun loginFailed()
}