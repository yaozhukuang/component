package com.zw.yzk.module.article.ui.view

import com.zw.yzk.module.article.data.Article
import com.zw.yzk.module.article.data.BannerEntity

/**
 * @author zhanwei
 */
interface ArticleListView{

    fun setArticleList(articleList: List<Article>)

    fun getArticleListFailed()

    fun setBannerList(list: List<BannerEntity>)
}