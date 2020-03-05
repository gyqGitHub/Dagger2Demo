package com.example.daggerdemo.auto.dagger.provide.depend

import javax.inject.Inject

/**
 * 通过Inject注解构造函数进行依赖注入，编译器会自动生成提供该依赖类的工厂类(如dagger/app/build/generated/source/kapt/debug/com/example/daggerdemo/auto/dagger/provide/depend/Pig_Factory.java)
 * 通过查看编译器根据Dagger2注解自动生成的类可以发现很多东西
 * @author gyq
 * @date 2020-03-05
 */
class Pig @Inject constructor():Animal{
    override fun description(){
        println("我是小猪佩奇,通过@Inject提供")
    }
}