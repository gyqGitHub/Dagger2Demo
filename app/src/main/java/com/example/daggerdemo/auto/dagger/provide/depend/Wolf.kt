package com.example.daggerdemo.auto.dagger.provide.depend

import javax.inject.Inject

/**
 * 既可通过@Inject提供也可由module提供时，以module提供的为准，可看编译器根据Dagger2类注解自动生成的类
 * @author gyq
 * @date 2020-03-05
 */
class Wolf @Inject constructor():Animal {

    var description:String = "我是灰太狼，通过@Inject提供"

    override fun description(){
        println(description)
    }
}