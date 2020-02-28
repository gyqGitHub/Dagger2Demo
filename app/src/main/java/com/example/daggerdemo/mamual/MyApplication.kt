package com.example.daggerdemo.mamual

import android.app.Application

/**
 * 因为Retrofit对象的生命周期应该是app的生命周期，所以放在Application中
 * @author gyq
 * @date 2020-02-24
 */
class MyApplication : Application() {

    // Instance of AppContainer that will be used by all the Activities of the app
    val appContainer = AppContainer()
}