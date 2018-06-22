package com.zw.yzk.module.article.repository

import com.zw.yzk.component.common.base.BaseRepository
import com.zw.yzk.component.common.constant.ARTICLE_LIST
import com.zw.yzk.component.common.constant.GSON
import com.zw.yzk.component.network.bean.ResponseTransformer
import com.zw.yzk.module.article.data.ArticleResponse
import com.zw.yzk.module.article.data.ArticleTask
import io.reactivex.Observable
import okhttp3.ResponseBody

/**
 * @author zhanwei
 */
class ArticleRepository : BaseRepository<ArticleTask>() {

    override fun connectServer(task: ArticleTask): Observable<ResponseBody> {
        val path = "$ARTICLE_LIST${task.index}$GSON"
        return service.get(path, task.headers, task.params)
    }

    override fun getDataTransformer(): ResponseTransformer<ArticleResponse> {
        return ResponseTransformer<ArticleResponse>(ArticleResponse::class.java, gson)
    }

}