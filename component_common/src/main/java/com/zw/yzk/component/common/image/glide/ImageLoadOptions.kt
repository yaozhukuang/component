package com.zw.yzk.component.common.image.glide

import android.graphics.drawable.Drawable
import android.widget.ImageView

import com.bumptech.glide.request.RequestOptions
import com.zw.yzk.component.common.R
import com.zw.yzk.component.common.image.Options


/**
 * Glide图片加载配置类
 */
class ImageLoadOptions() : Options<RequestOptions> {

    companion object {
        //默认图片占位和错误展示
        val DEFAULT_IMG_PLACE_HOLDER = R.drawable.component_res_ic_default_head
        val DEFAULT_IMG_ERROR = R.drawable.component_res_ic_default_head
        //默认头像占位和错误展示
        val DEFAULT_HEAD_PLACE_HOLDER = R.drawable.component_res_ic_default_head
        val DEFAULT_HEAD_ERROR = R.drawable.component_res_ic_default_head

        /**
         * 显示圆形，使用传入的占位图和错误图
         */
        fun circleCropOf(holder: Int, error: Int): ImageLoadOptions =
                ImageLoadOptions(holder, error).circleCrop()

        /**
         * 显示圆形，使用图片当前图片
         */
        fun circleCropOf(image: ImageView): ImageLoadOptions =
                ImageLoadOptions().circleCrop().placeHolder(image.drawable).error(DEFAULT_HEAD_ERROR)

        /**
         * 显示圆形，使用默认的占位图和错误图
         */
        fun circleCropOf(): ImageLoadOptions =
                ImageLoadOptions(DEFAULT_HEAD_PLACE_HOLDER, DEFAULT_HEAD_ERROR).circleCrop()

        /**
         * 使用默认占位图和错误图
         */
        fun defaultOption(): ImageLoadOptions =
                ImageLoadOptions(DEFAULT_IMG_PLACE_HOLDER, DEFAULT_IMG_ERROR)
    }

    private val options: RequestOptions = RequestOptions()

    constructor(holder: Int, error: Int) : this() {
        options.placeholder(holder).error(error)
    }

    /**
     * 设置显示的图片的宽高
     */
    fun override(width: Int, height: Int): ImageLoadOptions {
        options.override(width, height)
        return this
    }

    /**
     * 设置占位图
     */
    fun placeHolder(res: Int): ImageLoadOptions {
        options.placeholder(res)
        return this
    }

    /**
     * 设置占位图
     */
    fun placeHolder(drawable: Drawable?): ImageLoadOptions {
        options.placeholder(drawable)
        return this
    }

    /**
     * 设置错误图
     */
    fun error(res: Int): ImageLoadOptions {
        options.error(res)
        return this
    }

    /**
     * 设置图片缩放模式centerInside
     */
    fun centerInside(): ImageLoadOptions {
        options.centerInside()
        return this
    }

    /**
     * 设置图片缩放模式centerCrop
     */
    fun centerCrop(): ImageLoadOptions {
        options.centerCrop()
        return this
    }

    /**
     * 设置图片缩放模式fitCenter
     */
    fun fitCenter(): ImageLoadOptions {
        options.fitCenter()
        return this
    }

    /**
     * 设置图片显示为圆形
     */
    fun circleCrop(): ImageLoadOptions {
        options.circleCrop()
        return this
    }

    override fun getOptions(): RequestOptions = options

}