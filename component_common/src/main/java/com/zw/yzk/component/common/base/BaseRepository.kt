package com.zw.yzk.component.common.base

import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.zw.yzk.component.common.utils.StringTypeAdapter
import com.zw.yzk.component.network.exception.CustomException
import com.zw.yzk.component.network.repository.AbstractRepository
import io.reactivex.functions.Function
import okhttp3.ResponseBody
import org.json.JSONObject
import java.io.IOException

/**
 * 所有Repository基类，用于处理网络数据返回
 */
abstract class BaseRepository<T : BaseTask> : AbstractRepository<T>() {

    protected val gson = GsonBuilder().registerTypeAdapter(String::class.java, StringTypeAdapter()).create()!!

    override fun checkServerData(): Function<ResponseBody, String> {
        return Function { body ->
            try {
                val data = body.string()
                val json = JSONObject(data)
                val errCode = json.optInt("errorCode", -1)
                val errMsg = json.optString("errorMsg")
                return@Function if (errCode >= 0) {
                    data
                } else {
                    throw CustomException(errCode, errMsg)
                }
            } catch (e: JsonSyntaxException) {
                throw CustomException(CustomException.PARSE_ERROR)
            } catch (e: IOException) {
                throw CustomException(CustomException.PARSE_ERROR)
            }
        }
    }

}