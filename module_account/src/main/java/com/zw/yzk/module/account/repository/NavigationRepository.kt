package com.zw.yzk.module.account.repository

import com.zw.yzk.component.common.base.BaseRepository
import com.zw.yzk.component.common.base.BaseTask
import com.zw.yzk.component.common.constant.GSON
import com.zw.yzk.component.common.constant.NAVIGATION
import com.zw.yzk.component.network.bean.ResponseTransformer
import com.zw.yzk.module.account.data.NavigationResponse
import io.reactivex.Observable


class NavigationRepository: BaseRepository<BaseTask>() {
    override fun connectServer(task: BaseTask): Observable<*> {
        return service.get("$NAVIGATION$GSON", task.headers, task.params)
    }

    override fun getDataTransformer(): ResponseTransformer<NavigationResponse> {
        return ResponseTransformer<NavigationResponse>(NavigationResponse::class.java)
    }
}