package com.zw.yzk.module.account.data

import com.zw.yzk.component.common.base.BaseTask

/**
 * 用户登录
 */
data class LoginTask(val account: String, val password: String): BaseTask()

/**
 * 收藏的文章列表
 */
data class ArticleTask(val index: Int): BaseTask()
