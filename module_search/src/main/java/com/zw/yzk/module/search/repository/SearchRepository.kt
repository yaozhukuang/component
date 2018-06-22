package com.zw.yzk.module.search.repository

import com.zw.yzk.component.common.base.BaseRepository
import com.zw.yzk.component.common.constant.GSON
import com.zw.yzk.component.common.constant.SEARCH
import com.zw.yzk.component.network.bean.ResponseTransformer
import com.zw.yzk.module.search.data.SearchResponse
import com.zw.yzk.module.search.data.SearchTask
import io.reactivex.Observable


class SearchRepository: BaseRepository<SearchTask>() {
    override fun connectServer(task: SearchTask): Observable<*> {
        val url = "$SEARCH${task.index}$GSON"
        task.params["k"] = task.key
        return service.post(url, task.headers, task.params)
    }

    override fun getDataTransformer(): ResponseTransformer<SearchResponse> {
        return ResponseTransformer<SearchResponse>(SearchResponse::class.java)
    }
}