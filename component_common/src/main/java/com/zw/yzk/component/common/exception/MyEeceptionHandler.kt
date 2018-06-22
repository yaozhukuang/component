package com.zw.yzk.component.common.exception

import android.content.Context
import android.text.TextUtils

import com.zw.yzk.component.common.R
import com.zw.yzk.component.network.exception.CustomException
import com.zw.yzk.component.network.exception.DefaultHandler
import com.zw.yzk.component.widget.toast.ToastManager

/**
 * @author zhanwei
 */
class MyEeceptionHandler(context: Context) : DefaultHandler(context) {

    override fun dealErrorMessage(msg: String, errorCode: Int) {
        if (TextUtils.isEmpty(msg)) {
            when (errorCode) {
                CustomException.UNKNOWN_ERROR -> showErrorMessage(context.resources.getString(R.string.component_network_unknown_error), errorCode)
                CustomException.UNKNOWN_INVALID_TOKEN -> {
                }
                CustomException.MUST_LOGIN -> {
                }
                else -> showErrorMessage(context.resources.getString(R.string.component_network_connect_error), errorCode)
            }
        } else {
            showErrorMessage(msg, errorCode)
        }
    }

    /**
     * 展示错误信息
     *
     * @param msg 错误信息
     */
    private fun showErrorMessage(msg: String, errorCode: Int) {
        var errorInfo = msg
        if (errorCode != 0) {
            errorInfo = "$msg($errorCode)"
        }
        ToastManager.getInstance().showToast(context, errorInfo)
    }
}
