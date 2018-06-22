package com.zw.yzk.component.common.network

import android.content.Context
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.zw.yzk.component.network.config.BodyConfig
import com.zw.yzk.component.network.interceptor.ErrorInterceptor
import com.zw.yzk.component.network.interceptor.LoggingInterceptor
import okhttp3.*
import java.util.concurrent.TimeUnit

class CookieConfig(context: Context, baseUrl: String): BodyConfig(context, baseUrl) {

    override fun getOkHttpClient(): OkHttpClient {
        if (logger == null) {
            logger = LoggingInterceptor.Logger.DEFAULT
        }
        val level = if (debug) LoggingInterceptor.Level.BODY else LoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .cache(Cache(context.cacheDir, (20 * 1024 * 1024).toLong()))
                .addInterceptor(LoggingInterceptor(logger).setLevel(level))
                .addInterceptor(ErrorInterceptor())
                .cookieJar(PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context)))
                .build()
    }
}