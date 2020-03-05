package com.example.daggerdemo.auto.dagger.provide.depend

import javax.inject.Inject

/**
 *
 * @author gyq
 * @date 2020-03-05
 */
class Cat @Inject constructor() :Animal{
    override fun description(){
        println("我是多啦A梦，通过@Binds 和 @Inject 提供")
    }
}