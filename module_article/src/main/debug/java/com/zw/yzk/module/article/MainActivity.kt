package com.zw.yzk.module.article

import android.os.Bundle
import com.zw.yzk.component.banner.DefaultIndicator
import com.zw.yzk.component.common.base.BaseActivity
import com.zw.yzk.component.common.image.ImageLoader
import com.zw.yzk.component.common.utils.LogUtils
import com.zw.yzk.component.widget.toast.ToastManager
import com.zw.yzk.module.article.ui.fragment.ArticleListFragment
import kotlinx.android.synthetic.main.module_article_activity_main.*


class MainActivity : BaseActivity() {

    private var articleFragmentFragment: ArticleListFragment? = null

    override fun getLayoutId() = R.layout.module_article_activity_main

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
                    addFragment()
                }
                1 -> {
                    showToast("点击item1")
                }
                2 -> {
                    showToast("点击item2")
                }
                3 -> {
                    showToast("点击item3")
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
        if (articleFragmentFragment == null) {
            articleFragmentFragment = ArticleModuleImpl().getArticleFragment()
            fragmentTransaction
                    .add(R.id.container, ArticleModuleImpl().getArticleFragment())
        } else {
            fragmentTransaction.show(articleFragmentFragment)
        }
        fragmentTransaction.commit()

    }

    private fun showToast(msg: String) {
        ToastManager.getInstance().showToast(this, msg)
    }


}
