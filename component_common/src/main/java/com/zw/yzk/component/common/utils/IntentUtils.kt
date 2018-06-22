package com.zw.yzk.component.common.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.zw.yzk.component.common.R
import com.zw.yzk.component.widget.toast.ToastManager


class IntentUtils {

    companion object {
        fun startBroswer(context: Context, url: String) {
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)

            if (checkIntent(context, intent)) {
                context.startActivity(intent)
            } else {
                ToastManager.getInstance().showToast(context,
                        context.resources.getString(R.string.component_res_activity_invalid))
            }
        }

        fun checkIntent(context: Context, intent: Intent): Boolean {
            val cn = intent.resolveActivity(context.packageManager)
            return cn != null
        }
    }
}