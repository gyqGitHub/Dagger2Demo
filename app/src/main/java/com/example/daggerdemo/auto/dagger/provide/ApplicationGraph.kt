package com.example.daggerdemo.auto.dagger.provide

import dagger.Component
import okhttp3.Interceptor

/**
 * 通过在接口上的@Component注解创建依赖关系图===>（解决依赖哪里来，哪里去）
 *     1.通过@Component注解，编译器会自动创建一个实现了该接口的构造者模式的实现类(相当于手动创建的容器)，
 *
 *     2.==>解决依赖哪里来：该容器内的提供依赖的类型就是接口内暴露出来的函数（如repository()）的返回值类型（如UserRepository）
 *      （编译器自动生成的类：dagger/app/build/generated/source/kapt/debug/com/example/daggerdemo/auto/dagger/basic/DaggerApplicationGraph.java）
 *        怎么来？？？
 *        1。@Inject注解构造函数
 *        2。module 中@Provides 和 @Binds 修饰的函数
 *
 *     3.==>解决依赖哪里去（把依赖注入到哪里去）？？？
 *       看dagger/app/src/main/java/com/example/daggerdemo/auto/dagger/inject下示例
 *
 * 发现该组件(如DaggerApplicationGraph)每次提供的依赖都是重新创建的，怎么避免每次都重新创建呢？
 * 看dagger/app/src/main/java/com/example/daggerdemo/auto/dagger/scope下示例
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
    //这里提出一个问题是否只有使用了作用域注解的依赖类的实现才会由工厂类提供，没有使用的直接通过构造器创建？？？
    //请看dagger/app/src/main/java/com/example/daggerdemo/auto/dagger/scope下示例

    println(applicationGraph.repository() == applicationGraph.repository())
}