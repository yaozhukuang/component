package com.zw.yzk.module.account.data

import com.google.gson.annotations.SerializedName
import com.zw.yzk.component.common.base.BaseEntity

/**
 * 登录用户
 */
data class LoginResponse(@SerializedName("data") val entity: LoginEntity) : BaseEntity()

data class LoginEntity(
        @SerializedName("email") val email: String,
        @SerializedName("icon") val icon: String,
        @SerializedName("id") val id: Int,
        @SerializedName("password") val password: String,
        @SerializedName("username") val username: String,
        @SerializedName("type") val type: Int,
        @SerializedName("collectIds") val collectedIds: List<Int>)

/**
 * 收藏的文章列表
 */
data class ArticleResponse(@SerializedName("data") val entity: ArticleEntity) : BaseEntity()
data class ArticleEntity(@SerializedName("datas") val list: List<Article>)
data class Article(
        @SerializedName("author") val author: String,
        @SerializedName("chapterId") val chapterId: Int,
        @SerializedName("id") val id: Int,
        @SerializedName("link") val link: String,
        @SerializedName("niceDate") val niceDate: String,
        @SerializedName("title") val title: String,
        @SerializedName("chapterName") val chapterName: String,
        @SerializedName("userId") val userId: Int)

/**
 * 收藏的网址
 */
data class WebSiteResponse(@SerializedName("data") val list: List<Website>) : BaseEntity()

data class Website(
        @SerializedName("id") val id: Int,
        @SerializedName("link") val link: String,
        @SerializedName("name") val name: String)

/**
 * 网页导航
 */
data class NavigationResponse(@SerializedName("data") val group: List<NavigationGroup>) : BaseEntity()

data class NavigationGroup(
        @SerializedName("articles") val children: List<Navigation>,
        @SerializedName("name") val name: String,
        @SerializedName("cid") val id: String)

data class Navigation(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("chapterId") val parentId: String,
        @SerializedName("link") val link: String)