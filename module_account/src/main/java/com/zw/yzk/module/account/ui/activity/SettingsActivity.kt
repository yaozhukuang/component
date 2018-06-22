package com.zw.yzk.module.account.ui.activity

import android.os.Bundle
import com.zw.yzk.component.common.base.BaseActivity
import com.zw.yzk.component.common.greendao.helper.UserHelper
import com.zw.yzk.component.common.utils.ActivityManager
import com.zw.yzk.component.common.utils.ToolbarBuilder
import com.zw.yzk.component.widget.toast.ToastManager
import com.zw.yzk.module.account.R
import kotlinx.android.synthetic.main.module_account_activity_settings.*

class SettingsActivity : BaseActivity() {


    override fun getLayoutId() = R.layout.module_account_activity_settings


    override fun initToolbar() {
        ToolbarBuilder.builder(this).setTitle(R.string.module_account_settings).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clear.setOnClickListener {
            ToastManager.getInstance().showToast(
                    this@SettingsActivity, getString(R.string.module_account_clear_success))
        }
        logout.setOnClickListener {
            UserHelper.logout()
            ActivityManager.getInstance().finishAllActivity()
            startActivity(LoginActivity::class.java)
        }
    }

}