package com.example.daggerdemo.auto.dagger.provide.depend

import javax.inject.Inject

/**
 * 如果@Inject注解的构造函数是有参数的，那么这个参数也要是Dagger组件能提供的,如这个name是由ProvideModule的provideMouseName()方法提供的
 * @author gyq
 * @date 2020-03-05
 */
class Mouse @Inject constructor(val name:String):Animal{
    override fun description() {
        println("我是$name,通过@Inject方式提供")
    }

}