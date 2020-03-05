package com.example.daggerdemo.auto.dagger.provide.depend

/**
 * 通过module的@Provides注解提供
 * @author gyq
 * @date 2020-03-05
 */
class Sheep constructor(private val name:String):Animal {
    override fun description(){
        println("我是$name,通过module的@Provides注解提供")
    }
}