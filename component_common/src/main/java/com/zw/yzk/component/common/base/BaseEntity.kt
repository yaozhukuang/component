package com.zw.yzk.component.common.base

import com.google.gson.annotations.SerializedName

/**
 * 所有网络解析数据的基类
 */
open class BaseEntity{
    //返回码，需要确认是否是整型
    @SerializedName("errorCode")
    var errCode: Int = 0
    //返回信息
    @SerializedName("errorMsg")
    var msg: String? = null
}