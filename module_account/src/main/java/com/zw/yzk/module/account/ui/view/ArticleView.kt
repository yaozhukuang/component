package com.zw.yzk.module.account.ui.view

import com.zw.yzk.module.account.data.Article

interface ArticleView {
    fun setArticleList(list: List<Article>)
    fun getArticleListFailed()
}