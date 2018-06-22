package com.zw.yzk.component.common.base

import com.zw.yzk.component.network.observer.DefaultObserver

/**
 * 所有observer基类
 */
open class BaseObserver<T>: DefaultObserver<T>() {
    override fun onNext(t: T) {}

    override fun onComplete() {}
}