package com.zw.yzk.module.search.repository

import com.zw.yzk.component.common.base.BaseRepository
import com.zw.yzk.component.common.base.BaseTask
import com.zw.yzk.component.common.constant.GSON
import com.zw.yzk.component.common.constant.HOTWORD
import com.zw.yzk.component.network.bean.ResponseTransformer
import com.zw.yzk.module.search.data.HotWordResponse
import io.reactivex.Observable


class HotWordRepository: BaseRepository<BaseTask>() {
    override fun connectServer(task: BaseTask): Observable<*> {
        return service.get("$HOTWORD$GSON", task.headers, task.params)
    }

    override fun getDataTransformer(): ResponseTransformer<HotWordResponse> {
        return ResponseTransformer<HotWordResponse>(HotWordResponse::class.java)
    }
}