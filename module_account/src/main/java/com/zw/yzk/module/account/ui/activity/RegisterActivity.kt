package com.zw.yzk.module.account.ui.activity

import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import com.zw.yzk.component.common.base.BasePresenterActivity
import com.zw.yzk.component.common.utils.ActivityManager
import com.zw.yzk.component.common.utils.ToolbarBuilder
import com.zw.yzk.component.widget.toast.ToastManager
import com.zw.yzk.module.account.R
import com.zw.yzk.module.account.data.LoginEntity
import com.zw.yzk.module.account.presenter.RegisterPresenter
import com.zw.yzk.module.account.ui.view.RegisterView
import kotlinx.android.synthetic.main.module_account_activity_register.*

class RegisterActivity : BasePresenterActivity<RegisterPresenter>(), RegisterView {


    override fun getLayoutId() = R.layout.module_account_activity_register

    override fun initPresenter() {
        presenter = RegisterPresenter()
        presenter?.setView(this)
    }

    override fun initToolbar() {
        ToolbarBuilder.builder(this).setTitle(R.string.module_account_register).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setClickListener()
    }

    override fun registerSuccess(entity: LoginEntity) {
        ToastManager.getInstance().showToast(this, R.string.module_account_register_success)
        ActivityManager.getInstance().finishActivity(LoginActivity::class.java)
        Handler().postDelayed({
            finish()
        }, 500)
    }

    override fun registerFailed() {
    }

    private fun setClickListener() {
        register.setOnClickListener {
            val accountStr = account.text.toString()
            val passwordStr = password.text.toString()
            val rePasswordStr = rePassword.text.toString()
            if (TextUtils.isEmpty(accountStr)) {
                ToastManager.getInstance().showToast(
                        this@RegisterActivity, R.string.module_account_please_input_account)
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(passwordStr)) {
                ToastManager.getInstance().showToast(
                        this@RegisterActivity, R.string.module_account_please_input_password)
                return@setOnClickListener
            }
            if(passwordStr != rePasswordStr) {
                ToastManager.getInstance().showToast(
                        this@RegisterActivity, R.string.module_account_password_check_failed)
                return@setOnClickListener
            }
            presenter?.login(this@RegisterActivity, accountStr, passwordStr)
        }
    }
}