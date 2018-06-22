package com.zw.yzk.component.common.module

import android.support.v4.app.Fragment

/**
 * @author zhanwei
 * module_article 模块对外暴露功能的接口
 */
interface ArticleModule: MService {

    /**
     * 返回文章Fragment
     */
    fun getArticleFragment(): Fragment
}