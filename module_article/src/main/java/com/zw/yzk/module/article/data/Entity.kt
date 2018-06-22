package com.zw.yzk.module.article.data

import com.google.gson.annotations.SerializedName
import com.zw.yzk.component.common.base.BaseEntity

/**
 * @author zhanwei
 */

/**
 * 文章列表
 */
data class ArticleResponse(@SerializedName("data") val entity: ArticleEntity) : BaseEntity()

data class ArticleEntity(@SerializedName("datas") val list: List<Article>?)
data class Article(
        @SerializedName("author") val author: String,
        @SerializedName("niceDate") val date: String,
        @SerializedName("desc") val des: String,
        @SerializedName("title") val title: String,
        @SerializedName("tags") val tags: List<ArticleTag>,
        @SerializedName("id") val id: Int,
        @SerializedName("link") val link: String,
        @SerializedName("chapterName") val type: String)

data class ArticleTag(
        @SerializedName("name") val name: String,
        @SerializedName("url") val url: String)


/**
 * 轮播图
 */
data class BannerResponse(@SerializedName("data") val list: List<BannerEntity>) : BaseEntity()

data class BannerEntity(
        @SerializedName("id") val id: String,
        @SerializedName("title") val title: String,
        @SerializedName("url") val url: String,
        @SerializedName("imagePath") val path: String)