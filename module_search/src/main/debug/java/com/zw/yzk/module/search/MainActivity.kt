package com.zw.yzk.module.search

import android.content.Intent
import android.os.Bundle
import com.zw.yzk.component.common.base.BaseActivity
import com.zw.yzk.module.search.ui.activity.SearchActivity
import kotlinx.android.synthetic.main.module_search_activity_main.*

class MainActivity: BaseActivity() {
    override fun getLayoutId() = R.layout.module_search_activity_main

    override fun initToolbar() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        search.setOnClickListener {
            startActivity(SearchActivity::class.java)
        }
    }


}