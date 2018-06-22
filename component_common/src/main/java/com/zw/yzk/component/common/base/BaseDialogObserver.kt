package com.zw.yzk.component.common.base


import android.support.v4.app.FragmentActivity
import com.zw.yzk.component.widget.dialog.LoadingDialog


/**
 * 带加载对话框的oberver
 */
open class BaseDialogObserver<T>(private val activity: FragmentActivity) : BaseObserver<T>() {
    private var loadingDialog: LoadingDialog? = null

    override fun onComplete() {
        dismissLoadingDialog()
        super.onComplete()
    }

    override fun onError(throwable: Throwable) {
        dismissLoadingDialog()
        super.onError(throwable)
    }

    override fun onStart() {
        showLoadingDialog(activity)
        super.onStart()
    }

    private fun showLoadingDialog(activity: FragmentActivity) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog()
        }
        //显示对话框
        if (!activity.isFinishing && !loadingDialog!!.isAdded && !loadingDialog!!.isVisible && !loadingDialog!!.isRemoving) {
            activity.supportFragmentManager.beginTransaction()
                    .add(loadingDialog, LoadingDialog::javaClass.name)
                    .commitAllowingStateLoss()
            activity.supportFragmentManager.executePendingTransactions()
        }
    }

    private fun dismissLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog!!.disMissDialog()
        }
    }
}