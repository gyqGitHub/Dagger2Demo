package com.example.daggerdemo.auto.dagger.provide

import dagger.Component
import okhttp3.Interceptor
import javax.inject.Scope
import javax.inject.Singleton

/**
 * 通过在接口上的@Component注解创建依赖关系图（解决依赖哪里来，哪里去）
 *     1.通过@Component注解，编译器会自动创建一个实现了该接口的构造者模式的实现类(相当于手动创建的容器)，
 *
 *     2.解决依赖哪里来：该容器内的提供依赖的类型就是接口内暴露出来的函数（如repository()）的返回值类型（如UserRepository）
 *      （编译器自动生成的类：dagger/app/build/generated/source/kapt/debug/com/example/daggerdemo/auto/dagger/basic/DaggerApplicationGraph.java）
 *        怎么来？？？
 *        1。@Inject注解构造函数
 *        2。module 中@Provides 和 @Binds 修饰的函数
 *
 *     3.解决依赖哪里去（把依赖注入到哪里去）？？？
 *       看dagger/app/src/main/java/com/example/daggerdemo/auto/dagger/android下示例
 *
 * 发现该组件(如DaggerApplicationGraph)每次提供的依赖都是重新创建的，怎么避免每次都重新创建呢？
 * 那就是使用作用域注解（如系统已有的@Singleton注解），让dagger知道返回同一个实例（怎么做到的？）
 *     使用作用域注解分两步走：1.用@Singleton注解@Component注解的接口(如ApplicationGraph)
 *                          2.用@Singleton注解依赖类(如UserRepository)或者 module(如 NetworkModule) 中提供依赖类的方法(如 provideLoginRetrofitService())
 *
 *     注意：作用域注解不会影响具体提供依赖类的地方(UserRepository_Factory,NetworkModule_ProvideLoginRetrofitService$app_debugFactory)，
 *          只会影响最终提供给依赖类的地方(如 DaggerApplicationGraph )
 *
 * 也可以创建自己的作用域注解(如MyCustomScope)
 *
 * @author gyq
 * @date 2020-02-24
 */
@Component(modules = [NetworkModule::class, UserModule::class])
interface ApplicationGraph {
    fun repository(): UserRepository

    fun getLoginService():LoginService

    fun getLoginRetrofitService():LoginRetrofitService

    fun getInterceptor():Interceptor

    fun getUser():UserService
}

/**
 * dagger组件的创建：
 *      1。建造者模式
 *      2。静态方法create()快速创建
 */
fun main(args: Array<String>) {

    //1。建造者模式
    var applicationGraph = DaggerApplicationGraph
        .builder()
        .networkModule(NetworkModule("gyq"))
        .build()

    //2。静态方法create()快速创建，因为NetWorkModule声明了有参构造函数，下面一行注释的代码将会报错
//    applicationGraph = DaggerApplicationGraph.create()

    //怎么避免每次都重新创建呢？？？
    //答案：作用域注解，让dagger知道在该作用域下返回同一个实例（怎么做到的？）
    //          答案：使用了@Singleton的组件Comment(ApplicationGraph)的编译器实现类(DaggerApplicationGraph)中会生成一个特殊依赖类提供者(DoubleCheck)，
    //               这个特殊的依赖类提供者提供的依赖实现就是同一个，要保证在作用域下是同一个依赖，需要加锁有一定的性能损耗

    //========？？？？这里提出一个问题是否只有使用了作用域注解的依赖类的实现才会由工厂类提供，没有使用的直接通过构造器创建？？？？===========
    //答案：通过编译器自动生成的类来看：依赖类的提供方式有三种：@Inject，@Binds和@Provides注解方式。
    //     1。在没有使用作用域注解的情况下，在生成的dagger组件(如DaggerApplicationGraph)中可以看出，
    //        只有@Provids方式才会由生成的工厂类提供(其实最终是由该类中的module提供的)，其他方式都是直接创建
    //     2。在使用了作用域注解的情况下，所有方式都是由生成的工厂类提供
    println(applicationGraph.repository() == applicationGraph.repository())
}