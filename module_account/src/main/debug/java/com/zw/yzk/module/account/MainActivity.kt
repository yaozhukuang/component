package com.zw.yzk.module.account

import android.os.Bundle
import android.support.v4.app.Fragment
import com.zw.yzk.component.common.base.BaseActivity
import com.zw.yzk.component.common.greendao.helper.UserHelper
import com.zw.yzk.component.common.utils.LogUtils
import com.zw.yzk.component.widget.toast.ToastManager
import com.zw.yzk.module.account.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.module_account_activity_main.*


class MainActivity : BaseActivity() {

    private var accountFragment: Fragment? = null

    override fun getLayoutId() = R.layout.module_account_activity_main

    override fun initToolbar() {
        LogUtils.d("initToolBar")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        bottomMenu.setTextColor(R.color.component_res_selector_tv_color_red)
                .add(getString(R.string.component_res_article), R.drawable.component_res_selector_main_article)
                .add(getString(R.string.component_res_project), R.drawable.component_res_selector_main_project)
                .add(getString(R.string.component_res_me), R.drawable.component_res_selector_main_me)
                .create()
        bottomMenu.setOnItemClickedListener { _, index ->
            when (index) {
                0 -> {
                    showToast("点击item0")
                }
                1 -> {
                    showToast("点击item1")
                }
                2 -> {
                    if (UserHelper.checkLogin()) {
                        addFragment()
                    } else {
                        startActivity(LoginActivity::class.java)
                        bottomMenu.setChecked(0)
                    }
                }
                else -> {
                    showToast("点击else")
                }
            }
        }
        bottomMenu.setChecked(0)
    }

    private fun addFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (accountFragment == null) {
            accountFragment = AccountModuleImpl().getMyAccountFragment()
            fragmentTransaction
                    .add(R.id.container, AccountModuleImpl().getMyAccountFragment())
        } else {
            fragmentTransaction.show(accountFragment)
        }
        fragmentTransaction.commit()

    }

    private fun showToast(msg: String) {
        ToastManager.getInstance().showToast(this, msg)
    }


}
