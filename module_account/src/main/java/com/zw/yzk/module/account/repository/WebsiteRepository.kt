package com.zw.yzk.module.account.repository

import com.zw.yzk.component.common.base.BaseRepository
import com.zw.yzk.component.common.base.BaseTask
import com.zw.yzk.component.common.constant.GSON
import com.zw.yzk.component.common.constant.WEBSITE
import com.zw.yzk.component.network.bean.ResponseTransformer
import com.zw.yzk.module.account.data.WebSiteResponse
import io.reactivex.Observable

class WebsiteRepository: BaseRepository<BaseTask>() {
    override fun connectServer(task: BaseTask): Observable<*> {
        return service.get("$WEBSITE$GSON", task.headers, task.params)
    }

    override fun getDataTransformer(): ResponseTransformer<WebSiteResponse> {
        return ResponseTransformer<WebSiteResponse>(WebSiteResponse::class.java)
    }
}