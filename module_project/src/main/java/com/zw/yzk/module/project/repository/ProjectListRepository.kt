package com.zw.yzk.module.project.repository

import com.zw.yzk.component.common.base.BaseRepository
import com.zw.yzk.component.common.constant.GSON
import com.zw.yzk.component.common.constant.PROJECT_LIST
import com.zw.yzk.component.network.bean.ResponseTransformer
import com.zw.yzk.module.project.data.ProjectListResponse
import com.zw.yzk.module.project.data.ProjectListTask
import io.reactivex.Observable

class ProjectListRepository: BaseRepository<ProjectListTask>() {
    override fun connectServer(task: ProjectListTask): Observable<*> {
        val path = "$PROJECT_LIST${task.index}$GSON"
        task.params["cid"] = task.cId.toString()
        return service.get(path, task.headers, task.params)
    }

    override fun getDataTransformer():  ResponseTransformer<ProjectListResponse> {
        return ResponseTransformer<ProjectListResponse>(ProjectListResponse::class.java, gson)
    }
}