package com.zw.yzk.component.common.image.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import java.io.InputStream
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.LibraryGlideModule

/**
 * 自定义GlideModule，可以进行图片加载的全局配置
 */
@GlideModule
class MyGlideModule : LibraryGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        //使用Okhttp请求网络
        registry.append(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory())
    }
}