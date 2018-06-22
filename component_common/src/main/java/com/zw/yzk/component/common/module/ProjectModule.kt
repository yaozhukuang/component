package com.zw.yzk.component.common.module

import android.support.v4.app.Fragment

/**
 * @author zhanwei
 * module_project 模块对外暴露功能的接口
 */
interface ProjectModule: MService {

    /**
     * 返回项目分类Fragment
     * @return 项目分类Fragment
     */
    fun getProjectTreeFragment(): Fragment
}