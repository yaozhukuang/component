package com.zw.yzk.module.project.data

import com.zw.yzk.component.common.base.BaseTask

/**
 * 文章列表请求task
 */
data class ProjectListTask(val cId: Int, val index: Int) : BaseTask()
