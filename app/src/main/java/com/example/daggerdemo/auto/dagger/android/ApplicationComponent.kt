package com.example.daggerdemo.auto.dagger.android

import dagger.Component
import javax.inject.Singleton

/**
 *
 * @author gyq
 * @date 2020-02-24
 */
@Singleton
@Component(modules = [NetworkModule::class,SubcomponentsModule::class])
interface ApplicationComponent {
    fun repository(): UserRepository

//    fun inject(activity: LoginActivity)

    fun loginComponent(): LoginComponent.Factory
}

