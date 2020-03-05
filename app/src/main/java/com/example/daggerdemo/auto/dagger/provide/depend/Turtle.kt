package com.example.daggerdemo.auto.dagger.provide.depend

/**
 *
 * @author gyq
 * @date 2020-03-05
 */
class Turtle :Animal{
   override fun description(){
        println("我是龟仙人，通过module的@Provides注解提供")
    }
}