package com.zw.yzk.module.search

import android.content.Context
import android.content.Intent
import com.zw.yzk.component.annotation.XModule
import com.zw.yzk.component.common.constant.MODULE_SEARCH
import com.zw.yzk.component.common.module.SearchModule
import com.zw.yzk.module.search.ui.activity.SearchActivity

@XModule(MODULE_SEARCH)
class SearchModuleImpl: SearchModule {
    override fun startSearchActivity(context: Context) {
        context.startActivity(Intent(context, SearchActivity::class.java))
    }
}