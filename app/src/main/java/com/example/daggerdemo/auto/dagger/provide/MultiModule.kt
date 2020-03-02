package com.example.daggerdemo.auto.dagger.provide

import dagger.Module
import dagger.Provides

/**
 *
 * @author gyq
 * @date 2020-03-02
 */
@Module
class MultiModule{

    @Provides
    fun getMulti():MultiData{
        return MultiData("gyq")
    }
}