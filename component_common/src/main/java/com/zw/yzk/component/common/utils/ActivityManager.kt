package com.zw.yzk.component.common.utils

import android.support.v7.app.AppCompatActivity

import java.util.Stack

/**
 * @author zhanwei
 * 自定义Activity栈，用于管理activity
 */
class ActivityManager private constructor() {

    private var activityStack: Stack<AppCompatActivity>? = null

    companion object {

        private val instance = ActivityManager()

        fun getInstance(): ActivityManager = instance
    }

    /**
     * 获取最后一个入栈的Activity
     * @return activity
     */
    fun getTopActivity(): AppCompatActivity? {
        return if (activityStack == null) {
            null
        } else {
            activityStack!!.lastElement()
        }
    }

    /**
     * 获取指定Activity实例
     * @param cls activity类
     * @return activity
     */
    fun getActivity(cls: Class<*>): AppCompatActivity? {
        if (activityStack == null) {
            return null
        }
        var returnActivity: AppCompatActivity? = null
        for (activity in activityStack!!) {
            if (activity.javaClass == cls) {
                returnActivity = activity
            }
        }
        return returnActivity
    }

    /**
     * 添加Activity到栈
     * @param activity 添加对象
     */
    fun addActivity(activity: AppCompatActivity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    /**
     * 将Activity从栈中移除
     * @param activity 移除对象
     */
    fun removeActivity(activity: AppCompatActivity?) {
        if (activityStack == null || activityStack!!.isEmpty() || activity == null) {
            return
        }
        activityStack!!.remove(activity)
    }

    /**
     * 结束指定的Activity
     * @param activity 结束Activity
     */
    fun finishActivity(activity: AppCompatActivity?) {
        if (activity != null && activityStack != null) {
            activityStack!!.remove(activity)
            activity.finish()
        }
    }

    /**
     * 结束指定类名的Activity
     * @param cls 指定Activity
     */
    fun finishActivity(cls: Class<*>) {
        if (activityStack == null) {
            return
        }
        var deleteActivity: AppCompatActivity? = null
        for (activity in activityStack!!) {
            if (activity.javaClass == cls) {
                deleteActivity = activity
            }
        }

        finishActivity(deleteActivity)
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack!!.size
        while (i < size) {
            if (null != activityStack!![i]) {
                activityStack!![i].finish()
            }
            i++
        }
        activityStack!!.clear()
    }

}
