package com.zw.yzk.component.common.constant


/**
 * 网络请求baseUrl
 */
const val BASE_URL = "http://www.wanandroid.com/"
const val GSON = "/json"

/**
 * 请求文章列表
 * eg：http://www.wanandroid.com/article/list/0/json
 */
const val ARTICLE_LIST = "article/list/"

/**
 * 请求轮播图
 * eg:http://www.wanandroid.com/banner/json
 */
const val BANNER = "banner"

/**
 * 请求项目分类
 * eg:http://www.wanandroid.com/project/tree/json
 */
const val PROJECT_TREE = "project/tree"

/**
 * 请求项目列表
 * eg:http://www.wanandroid.com/project/list/1/json?cid=294
 */
const val PROJECT_LIST = "project/list/"

/**
 * 登录
 * eg:http://www.wanandroid.com/user/login
 */
const val LOGIN = "user/login"

/**
 * 注册
 * eg:http://www.wanandroid.com/user/register
 */
const val REGISTER = "user/register"

/**
 * 收藏的文章列表
 * eg:http://www.wanandroid.com/lg/collect/list/0/json
 */
const val ARTICLE = "lg/collect/list/"

/**
 * 收藏的网站列表
 * eg:http://www.wanandroid.com/lg/collect/usertools/json
 */
const val WEBSITE = "lg/collect/usertools"

/**
 * 搜索热词
 * eg:http://www.wanandroid.com/hotkey/json
 */
const val HOTWORD = "hotkey"

/**
 * 搜索文章
 * eg:http://www.wanandroid.com/article/query/0/json
 * 在post方法body中加k：xxx
 */
const val SEARCH = "article/query/"

/**
 * 搜索热词
 * eg:http://www.wanandroid.com/navi/json
 */
const val NAVIGATION = "navi"


/**
 * module_account module注解参数
 */
const val MODULE_ACCOUNT = "Account"

/**
 * module_project module注解参数
 */
const val MODULE_PROJECT = "Project"

/**
 * module_article module注解参数
 */
const val MODULE_ARTICLE = "Article"

/**
 * module_search module注解参数
 */
const val MODULE_SEARCH = "Search"

/**
 * app 注解参数
 */
const val MODULE_APP = "App"