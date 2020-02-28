package com.example.daggerdemo.auto.dagger.provide

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * @author gyq
 * @date 2020-02-25
 */
@Singleton
class RequestInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response? {
        return null
    }
}