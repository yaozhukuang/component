package com.zw.yzk.module.account.glide

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

/**
 * 自定义GlideModule，可以进行图片加载的全局配置
 */
@GlideModule
class GlideAppModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.apply {
            //设置图片编码方式
            setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_RGB_565))

            //自定义图片缓存位置和代销
            val diskSize = 1024 * 1024 * 100
            //setDiskCache(InternalCacheDiskCacheFactory(context, diskSize))  //内存中
            setDiskCache(ExternalCacheDiskCacheFactory(context, "cache", diskSize)) //sd卡中

            // 自定义内存和图片池大小
            val memorySize = Runtime.getRuntime().maxMemory().toInt() / 8  // 取1/8最大内存作为最大缓存
            setMemoryCache(LruResourceCache(memorySize.toLong()))
            setBitmapPool(LruBitmapPool(memorySize.toLong()))
        }
    }

    override fun isManifestParsingEnabled(): Boolean = false
}