package com.zw.yzk.component.common.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zw.yzk.component.common.utils.ActivityManager
import com.zw.yzk.component.common.utils.LogUtils

/**
 * 所有Activity基类
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        //添加Activity到Activity栈
        ActivityManager.getInstance().addActivity(this)
        //初始化导航栏
        initToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        //将Activity从Activity栈移除
        ActivityManager.getInstance().removeActivity(this)
    }

    /**
     * 获取布局文件ID
     * @return Activity布局文件
     */
    protected abstract fun getLayoutId(): Int

    /**
     * 创建导航栏
     */
    protected abstract fun initToolbar()

    /**
     * 打开新Activity
     */
    protected fun startActivity(cls: Class<*>?) {
        if (cls == null) {
            return
        }
        startActivity(Intent(this, cls))
    }
}