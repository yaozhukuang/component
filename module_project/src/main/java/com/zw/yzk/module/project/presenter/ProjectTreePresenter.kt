package com.zw.yzk.module.project.presenter

import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import com.zw.yzk.component.common.base.BaseDialogObserver
import com.zw.yzk.component.common.base.BaseTask
import com.zw.yzk.component.common.presenter.Presenter
import com.zw.yzk.component.network.usecase.DefaultUseCase
import com.zw.yzk.module.project.data.ProjectTreeResponse
import com.zw.yzk.module.project.repository.ProjectTreeRepository
import com.zw.yzk.module.project.ui.view.ProjectTreeView

class ProjectTreePresenter: Presenter {

    private var uProjectTree: DefaultUseCase<BaseTask>? = null
    private var view: ProjectTreeView? = null

    override fun onCreate() {
        uProjectTree = DefaultUseCase(ProjectTreeRepository())
    }

    override fun onDestroy() {
        uProjectTree?.dispose()
    }

    fun setView(view: ProjectTreeView) {
        this.view = view
    }

    /**
     * 获取项目分类
     */
    fun getProjectTree(activity: FragmentActivity) {
        uProjectTree?.execute(ProjectTreeObserver(view!!, activity), BaseTask())
    }

    private class ProjectTreeObserver(private val view: ProjectTreeView, activity: FragmentActivity):
            BaseDialogObserver<ProjectTreeResponse>(activity){

        override fun onNext(t: ProjectTreeResponse) {
            super.onNext(t)
            view.setProjectTree(t.list)
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)
            view.getProjectTreeFailed()
        }
    }
}