package com.zw.yzk.module.account.presenter

import android.content.Context
import android.os.Environment
import com.zw.yzk.component.common.presenter.Presenter
import com.zw.yzk.component.common.utils.LogUtils
import com.zw.yzk.component.network.bean.DEntity
import com.zw.yzk.component.network.bean.DTask
import com.zw.yzk.component.network.observer.AbstractDownloadObserver
import com.zw.yzk.component.network.repository.DefaultDownloadRepository
import com.zw.yzk.component.network.usecase.DefaultUseCase
import com.zw.yzk.module.account.ui.view.DownloadView



class DownloadPresenter(val view: DownloadView) : Presenter {

    private val uDownload = DefaultUseCase<DTask>(DefaultDownloadRepository())

    override fun onCreate() {
    }

    override fun onDestroy() {
        uDownload.dispose()
    }

    fun download(context: Context, url: String) {
        val task = DTask()
        task.url = url
        uDownload.execute(DownloadObserver(view, context, task), task)
    }

    private class DownloadObserver(val view: DownloadView, context: Context, task: DTask)
        : AbstractDownloadObserver(context, task) {
        override fun onComplete() {
            view.downloadComplete()
        }

        override fun onProgress(entity: DEntity) {
            view.download(entity.readLength.toFloat() * 100 / entity.fileLength.toFloat())
        }

        override fun onError(e: Throwable) {
            LogUtils.error(e)
            view.downloadFailed()
        }

    }
}