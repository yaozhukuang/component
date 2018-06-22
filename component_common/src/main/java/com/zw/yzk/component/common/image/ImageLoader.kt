package com.zw.yzk.component.common.image

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.zw.yzk.component.common.image.glide.GlideLoader
import com.zw.yzk.component.common.image.glide.ImageLoadOptions

/**
 * 图片加载调用
 */
class ImageLoader {
    companion object {
        fun load(context: Context, source: String?, target: ImageView) {
            if (source == null) {
                //source为空
                load(context, source, target, ImageLoadOptions.defaultOption())
            } else {
                if (source.startsWith("http")) {
                    //加载网络图片
                    val builder = LazyHeaders.Builder()
                    //可以添加自定义builder
                    load(context, GlideUrl(source, builder.build()), target)
                } else {
                    //加载本地图片
                    load(context, source, target, ImageLoadOptions.defaultOption())
                }
            }
        }

        fun load(context: Context, source: Any?, target: ImageView, options: ImageLoadOptions = ImageLoadOptions.defaultOption()) {
            source?.let {
                GlideLoader.instance.display(context, source, target, options)
            }
        }

    }
}