package com.zw.yzk.module.project.data

import com.google.gson.annotations.SerializedName
import com.zw.yzk.component.common.base.BaseEntity

/**
 * @author zhanwei
 */

/**
 * 文章分类
 */
data class ProjectTreeResponse(@SerializedName("data") val list: List<ProjectEntity>) : BaseEntity()

data class ProjectEntity(
        @SerializedName("courseId") val courseId: Int,
        @SerializedName("id") val id: Int,
        @SerializedName("order") val order: String,
        @SerializedName("name") val name: String,
        @SerializedName("parentChapterId") val parentChapterId: String,
        @SerializedName("visible") val visible: Int)

/**
 * 文章分类
 */
data class ProjectListResponse(@SerializedName("data") val entity: ProjectData) : BaseEntity()

data class ProjectData(@SerializedName("datas") val list: List<Project>)
data class Project(
        @SerializedName("author") val author: String,
        @SerializedName("niceDate") val date: String,
        @SerializedName("desc") val des: String,
        @SerializedName("title") val title: String,
        @SerializedName("envelopePic") val image: String,
        @SerializedName("projectLink") val projectLink: String,
        @SerializedName("id") val id: Int,
        @SerializedName("link") val link: String,
        @SerializedName("chapterName") val type: String)