package com.zw.yzk.module.account.repository

import com.zw.yzk.component.common.base.BaseRepository
import com.zw.yzk.component.common.constant.REGISTER
import com.zw.yzk.component.common.greendao.entity.User
import com.zw.yzk.component.common.greendao.helper.UserHelper
import com.zw.yzk.component.network.bean.ResponseTransformer
import com.zw.yzk.module.account.data.LoginResponse
import com.zw.yzk.module.account.data.LoginTask
import io.reactivex.Observable

/**
 * 处理登录逻辑
 */
class RegisterRepository: BaseRepository<LoginTask>() {
    override fun connectServer(task: LoginTask): Observable<*> {
        task.params["username"] = task.account
        task.params["password"] = task.password
        task.params["repassword"] = task.password
        return service.postField(REGISTER, task.headers, task.params)
    }

    override fun getDataTransformer(): ResponseTransformer<LoginResponse> {
        return object: ResponseTransformer<LoginResponse>(LoginResponse::class.java, gson) {

            override fun dealTargetData(data: LoginResponse): LoginResponse {
                val user = User()
                user.email = data.entity.email
                user.icon = data.entity.icon
                user.userId = data.entity.id
                user.password = data.entity.password
                user.userName = data.entity.username
                user.type = data.entity.type
                user.login = true
                user.collectId = data.entity.collectedIds

                UserHelper.saveLoginUser(user)
                return data
            }
        }
    }
}
