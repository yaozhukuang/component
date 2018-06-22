package com.zw.yzk.module.article

import com.zw.yzk.component.annotation.XModule
import com.zw.yzk.component.common.constant.MODULE_ARTICLE
import com.zw.yzk.component.common.module.ArticleModule
import com.zw.yzk.module.article.ui.fragment.ArticleListFragment

/**
 * @author zhanwei
 */
@XModule(MODULE_ARTICLE)
class ArticleModuleImpl: ArticleModule {
    override fun getArticleFragment() = ArticleListFragment()
}