package com.zw.yzk.component.common.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.webkit.*
import com.zw.yzk.component.common.R
import com.zw.yzk.component.common.constant.ACTIVITY_TITLE
import com.zw.yzk.component.common.constant.WEB_URL
import com.zw.yzk.component.common.utils.ToolbarBuilder
import kotlinx.android.synthetic.main.component_common_activity_web.*

/**
 * 待WebView的Activity基类
 */
class BaseWebActivity : BaseActivity() {

    companion object {
        fun startBaseWebActivity(context: Context, title: String, url: String) {
            val intent = Intent(context, BaseWebActivity::class.java)
            intent.putExtra(ACTIVITY_TITLE, title)
            intent.putExtra(WEB_URL, url)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId() = R.layout.component_common_activity_web

    override fun initToolbar() {
        val title = intent.getStringExtra(ACTIVITY_TITLE) ?: getString(R.string.component_common_details)
        ToolbarBuilder.builder(this).setTitle(title).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initWebView()
        starAnimation()

        webView.loadUrl(intent.getStringExtra(WEB_URL))

    }

    override fun onDestroy() {
        super.onDestroy()

        if (webView != null) {
            webView.destroy()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        //设置 缓存模式
        webView.settings.domStorageEnabled = true
        webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        webView.settings.setAppCachePath(cacheDir.absolutePath)
        webView.settings.defaultTextEncodingName = "utf-8"
        webView.settings.allowFileAccess = true
        webView.settings.setAppCacheEnabled(true)
        //缩放以适应屏幕
        //        webView.getSettings().setUseWideViewPort(true);
        //        webView.getSettings().setLoadWithOverviewMode(true);
        //设置WebView属性，能够执行JavaScript脚本
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                starAnimation()
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                stopAnimation()
            }

            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                super.onReceivedError(view, request, error)
                stopAnimation()
            }
        }
    }

    private fun starAnimation() {
        if (loading.visibility == View.GONE) {
            loading.visibility = View.VISIBLE
        }
        val animation = RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f)
        animation.duration = 1500
        animation.interpolator = LinearInterpolator()
        animation.repeatCount = Animation.INFINITE
        animation.fillAfter = true
        loading.startAnimation(animation)
    }

    private fun stopAnimation() {
        if (loading == null) {
            return
        }
        loading.clearAnimation()
        loading.visibility = View.GONE
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()// 返回前一个页面
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}