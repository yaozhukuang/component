package com.zw.yzk.component.common.image.glide

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.zw.yzk.component.common.image.Loader

/**
 * 使用Glide加载图片
 */
class GlideLoader private constructor(): Loader<ImageLoadOptions> {

    companion object {
         val instance: GlideLoader by lazy { GlideLoader() }
    }

    override fun display(context: Context, source: Any, target: ImageView, options: ImageLoadOptions) {
        Glide.with(context)
                .load(source)
                .apply(options.getOptions())
                .into(target)
    }

}