package com.example.daggerdemo.auto.dagger.android

import android.app.Application

/**
 *
 * @author gyq
 * @date 2020-02-24
 */
// appComponent lives in the Application class to share its lifecycle
class MyApplication: Application() {
    // Reference to the application graph that is used across the whole app
//    val appComponent = DaggerApplicationComponent.create()
    val appComponent = DaggerApplicationComponent.builder()
        .networkModule(NetworkModule())
//        .subcomponentsModule()
        .build()
}
