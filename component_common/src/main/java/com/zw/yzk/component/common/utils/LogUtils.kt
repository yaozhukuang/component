package com.zw.yzk.component.common.utils

import timber.log.Timber


/**
 * @author zhanwei
 */
class LogUtils {
    companion object {

        fun init(debug: Boolean) {
            if (debug) {
                Timber.plant(Timber.DebugTree())
            }
        }

        fun tag(tag: String) {
            Timber.tag(tag)
        }

        fun i(msg: String) {
            Timber.i(msg)
        }

        fun e(msg: String) {
            Timber.e(msg)
        }

        fun d(msg: String) {
            Timber.d(msg)
        }

        fun w(msg: String) {
            Timber.w(msg)
        }

        fun v(msg: String) {
            Timber.v(msg)
        }

        fun error(throwable: Throwable) {
            Timber.e(throwable)
        }
    }
}