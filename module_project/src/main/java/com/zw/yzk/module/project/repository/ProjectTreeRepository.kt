package com.zw.yzk.module.project.repository

import com.zw.yzk.component.common.base.BaseRepository
import com.zw.yzk.component.common.base.BaseTask
import com.zw.yzk.component.common.constant.GSON
import com.zw.yzk.component.common.constant.PROJECT_TREE
import com.zw.yzk.component.network.bean.ResponseTransformer
import com.zw.yzk.module.project.data.ProjectTreeResponse
import io.reactivex.Observable

class ProjectTreeRepository: BaseRepository<BaseTask>() {
    override fun connectServer(task: BaseTask): Observable<*> {
        val path = "$PROJECT_TREE$GSON"
        return service.get(path, task.headers, task.params)
    }

    override fun getDataTransformer():  ResponseTransformer<ProjectTreeResponse> {
        return ResponseTransformer<ProjectTreeResponse>(ProjectTreeResponse::class.java, gson)
    }
}