package com.example.daggerdemo.auto.dagger.provide.depend

import javax.inject.Inject

/**
 * @author gyq
 * @date 2020-03-05
 */
class Fish @Inject constructor(private val name:String):Animal {

    override fun description() {
        println("我是$name,通过具有有参构造函数的module提供")
    }

}