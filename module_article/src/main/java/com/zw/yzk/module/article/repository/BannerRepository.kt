package com.zw.yzk.module.article.repository

import com.zw.yzk.component.common.base.BaseRepository
import com.zw.yzk.component.common.base.BaseTask
import com.zw.yzk.component.common.constant.BANNER
import com.zw.yzk.component.common.constant.GSON
import com.zw.yzk.component.network.bean.ResponseTransformer
import com.zw.yzk.module.article.data.BannerResponse
import io.reactivex.Observable
import okhttp3.ResponseBody

/**
 * @author zhanwei
 */
class BannerRepository : BaseRepository<BaseTask>() {

    override fun connectServer(task: BaseTask): Observable<ResponseBody> {
        val path = "$BANNER$GSON"
        return service.get(path, task.headers, task.params)
    }

    override fun getDataTransformer(): ResponseTransformer<BannerResponse> {
        return ResponseTransformer<BannerResponse>(BannerResponse::class.java, gson)
    }

}