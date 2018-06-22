package com.zw.yzk.module.account.ui.activity

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import com.zw.yzk.component.common.base.BasePresenterActivity
import com.zw.yzk.component.common.utils.ToolbarBuilder
import com.zw.yzk.component.widget.toast.ToastManager
import com.zw.yzk.module.account.R
import com.zw.yzk.module.account.presenter.DownloadPresenter
import com.zw.yzk.module.account.ui.view.DownloadView
import kotlinx.android.synthetic.main.module_account_activity_download.*
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat


class DownloadActivity : BasePresenterActivity<DownloadPresenter>(), DownloadView {

    private val PERMISSION_READ_AND_WRITE = 1
    var pList = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun initPresenter() {
        presenter = DownloadPresenter(this)
    }

    override fun getLayoutId() = R.layout.module_account_activity_download

    override fun initToolbar() {
        ToolbarBuilder.builder(this).setTitle(R.string.module_account_download)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setClickListener()
        checkPermission()
    }

    override fun download(progress: Float) {
        val p = progress.toString() + "%"
        progressStr.text = p
    }

    override fun downloadFailed() {
        ToastManager.getInstance().showToast(this, "下载失败")
    }

    override fun downloadComplete() {
        ToastManager.getInstance().showToast(this, "下载完成")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_READ_AND_WRITE -> checkPermission()
            else -> {
            }
        }
    }

    private fun setClickListener() {
        download.setOnClickListener {
            val path = url.text.toString()
            if (TextUtils.isEmpty(path)) {
                ToastManager.getInstance().showToast(this, "请输入一个下载链接")
                return@setOnClickListener
            }
            presenter?.download(this@DownloadActivity, path)
        }
        test.setOnClickListener {
            val str = "http://ucdl.25pp.com/fs08/2017/11/01/5/110_8c24581bc4254dd9307447218891e622.apk"
            url.setText(str)
        }
    }

    /**
     * 检查是否有存储权限
     */
    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val list = MutableList(2) { _ ->  ""}
            pList.forEach {
                if (ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED) {
                    list.add(it)
                }
            }
            ActivityCompat.requestPermissions(this, list.toTypedArray(), PERMISSION_READ_AND_WRITE)
        }
    }
}