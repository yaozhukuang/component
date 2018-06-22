package com.zw.yzk.module.account.ui.activity

import android.os.Bundle
import android.text.TextUtils
import com.zw.yzk.component.common.base.BasePresenterActivity
import com.zw.yzk.component.common.utils.ToolbarBuilder
import com.zw.yzk.component.compiler.ModuleManager
import com.zw.yzk.component.widget.toast.ToastManager
import com.zw.yzk.module.account.R
import com.zw.yzk.module.account.data.LoginEntity
import com.zw.yzk.module.account.presenter.LoginPresenter
import com.zw.yzk.module.account.ui.view.LoginView
import kotlinx.android.synthetic.main.module_account_activity_login.*

class LoginActivity : BasePresenterActivity<LoginPresenter>(), LoginView {


    override fun getLayoutId() = R.layout.module_account_activity_login

    override fun initPresenter() {
        presenter = LoginPresenter()
        presenter?.setView(this)
    }

    override fun initToolbar() {
        ToolbarBuilder.builder(this).setTitle(R.string.module_account_login).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setClickListener()
    }

    override fun loginSuccess(entity: LoginEntity) {
        if (ModuleManager.getInstance().xModuleApp != null) {
           ModuleManager.getInstance().xModuleApp.startMainActivity(this)
        }
        finish()
    }

    override fun loginFailed() {
    }

    private fun setClickListener() {
        login.setOnClickListener {
            val accountStr = account.text.toString()
            val passwordStr = password.text.toString()
            if (TextUtils.isEmpty(accountStr)) {
                ToastManager.getInstance().showToast(
                        this@LoginActivity, R.string.module_account_please_input_account)
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(passwordStr)) {
                ToastManager.getInstance().showToast(
                        this@LoginActivity, R.string.module_account_please_input_password)
                return@setOnClickListener
            }
            presenter?.login(this@LoginActivity, accountStr, passwordStr)
        }
        register.setOnClickListener {
            startActivity(RegisterActivity::class.java)
        }
    }

}