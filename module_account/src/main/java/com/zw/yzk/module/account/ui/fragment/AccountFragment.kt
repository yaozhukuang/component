package com.zw.yzk.module.account.ui.fragment


import android.content.Intent
import com.zw.yzk.component.common.base.BaseFragment
import com.zw.yzk.component.common.greendao.entity.User
import com.zw.yzk.component.common.greendao.helper.UserHelper
import com.zw.yzk.component.common.image.ImageLoader
import com.zw.yzk.component.compiler.ModuleManager
import com.zw.yzk.component.widget.toast.ToastManager
import com.zw.yzk.module.account.R
import com.zw.yzk.module.account.ui.activity.*
import kotlinx.android.synthetic.main.module_account_fragment_account.*

/**
 * @author zhanwei
 */
class AccountFragment : BaseFragment() {

    private var user: User? = null

    override fun getLayoutId(): Int = R.layout.module_account_fragment_account

    override fun init() {
        setClickedListener()
    }

    override fun onResume() {
        super.onResume()
        user = UserHelper.getLoginUser()

        if (user == null) {
            setUserInfo("", getString(R.string.module_account_not_login))
        } else {
            setUserInfo(user!!.icon, user!!.userName)
        }
    }

    /**
     * 设置点击监听事件
     */
    private fun setClickedListener() {
        //收藏的文章
        layoutArticle.setOnClickListener {
            if (user == null) {
                showAccountError()
            } else {
                startActivity(Intent(activity, ArticleActivity::class.java))
            }
        }
        //收藏的网址
        layoutWebsite.setOnClickListener {
            if (user == null) {
                showAccountError()
            } else {
                startActivity(Intent(activity, WebsiteActivity::class.java))
            }
        }
        //网站导航
        layoutNavigation.setOnClickListener {
            if(user == null) {
                showAccountError()
            } else {
                startActivity(Intent(activity, NavigationActivity::class.java))
            }
        }
        //设置
        layoutSettings.setOnClickListener {
            if (user == null) {
                showAccountError()
            } else {
                startActivity(Intent(activity, SettingsActivity::class.java))
            }
        }
        //下载
        layoutDownload.setOnClickListener {
            if (user == null) {
                showAccountError()
            } else {
                startActivity(Intent(activity, DownloadActivity::class.java))
            }
        }
        //未登录的情况下登录
        head.setOnClickListener {
            if (user == null) {
                startActivity(Intent(activity, LoginActivity::class.java))
            }
        }
    }

    /**
     * 设置个人信息
     */
    private fun setUserInfo(url: String, userName: String) {
        ImageLoader.load(activity!!, url, head)
        name.text = userName
    }

    /**
     * 用户未登录的时候展示toast提示
     */
    private fun showAccountError() {
        ToastManager.getInstance().showToast(activity, R.string.module_account_not_login_hint)
    }
}