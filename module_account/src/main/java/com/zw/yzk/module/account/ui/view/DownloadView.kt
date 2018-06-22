package com.zw.yzk.module.account.ui.view

interface DownloadView {

    fun download(progress: Float)

    fun downloadFailed()

    fun downloadComplete()
}