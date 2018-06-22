package com.zw.yzk.component.common.image

import android.content.Context
import android.widget.ImageView


interface Loader<in O: Options<*>>{
    fun  display(context: Context, source: Any, target: ImageView, options: O)
}