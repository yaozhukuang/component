package com.zw.yzk.module.account.repository

import com.zw.yzk.component.common.base.BaseRepository
import com.zw.yzk.component.common.constant.ARTICLE
import com.zw.yzk.component.common.constant.GSON
import com.zw.yzk.component.network.bean.ResponseTransformer
import com.zw.yzk.module.account.data.ArticleResponse
import com.zw.yzk.module.account.data.ArticleTask
import io.reactivex.Observable

/**
 * 处理登录逻辑
 */
class ArticleRepository : BaseRepository<ArticleTask>() {
    override fun connectServer(task: ArticleTask): Observable<*> {
        val url = "$ARTICLE${task.index}$GSON"
        return service.get(url, task.headers, task.params)
    }

    override fun getDataTransformer(): ResponseTransformer<ArticleResponse> {
        return ResponseTransformer<ArticleResponse>(ArticleResponse::class.java, gson)
    }
}