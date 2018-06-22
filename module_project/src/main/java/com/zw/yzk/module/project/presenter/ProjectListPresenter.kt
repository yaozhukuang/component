package com.zw.yzk.module.project.presenter

import com.zw.yzk.component.common.base.BaseObserver
import com.zw.yzk.component.common.presenter.Presenter
import com.zw.yzk.component.network.usecase.DefaultUseCase
import com.zw.yzk.module.project.data.ProjectListResponse
import com.zw.yzk.module.project.data.ProjectListTask
import com.zw.yzk.module.project.repository.ProjectListRepository
import com.zw.yzk.module.project.ui.view.ProjectListView

class ProjectListPresenter: Presenter {

    private var uProjectList: DefaultUseCase<ProjectListTask>? = null
    private var view: ProjectListView? = null

    override fun onCreate() {
        uProjectList = DefaultUseCase(ProjectListRepository())
    }

    override fun onDestroy() {
        uProjectList?.dispose()
    }

    fun setView(view: ProjectListView) {
        this.view = view
    }

    /**
     * 获取项目分类
     */
    fun getProjectTree(cid: Int, index: Int) {
        uProjectList?.execute(ProjectListObserver(view!!), ProjectListTask(cid, index))
    }

    private class ProjectListObserver(private val view: ProjectListView):
            BaseObserver<ProjectListResponse>(){

        override fun onNext(t: ProjectListResponse) {
            super.onNext(t)
            view.setProjectList(t.entity.list)
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)
            view.getProjectListFailed()
        }
    }
}