package com.example.daggerdemo.auto.dagger.android

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 *
 * @author gyq
 * @date 2020-02-25
 */
@ActivityScope
class RequestInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response? {
        return null
    }
}