package com.zw.yzk.module.search.data

import com.google.gson.annotations.SerializedName
import com.zw.yzk.component.common.base.BaseEntity

data class SearchResponse(@SerializedName("data") val entity: SearchEntity) : BaseEntity()

data class SearchEntity(@SerializedName("datas") val list: List<Search>)
data class Search(
        @SerializedName("author") val author: String,
        @SerializedName("niceDate") val date: String,
        @SerializedName("desc") val des: String,
        @SerializedName("title") val title: String,
        @SerializedName("id") val id: Int,
        @SerializedName("link") val link: String,
        @SerializedName("chapterName") val type: String)

/**
 * 搜索热词
 */
data class HotWordResponse(@SerializedName("data") val list: List<HotWord>) : BaseEntity()

data class HotWord(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String)
