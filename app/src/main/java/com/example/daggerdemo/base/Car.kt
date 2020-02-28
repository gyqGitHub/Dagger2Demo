package com.example.daggerdemo.base

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * 一.什么是依赖注入？
 * 类通常需要引用其他类。例如，一个Car类可能需要引用一个Engine类，Car类与Engine就有了依赖关系
 * 类引用其他类的方式有哪些？
 *     1.该类直接创建所需的其他类。
 *     2.通过其他类（如工厂类等context.getSystemService()）获取。
 *     3.通过参数的形式（包括直接字段赋值，类似于setter方法）
 *
 * 只有第三种引用方式才是依赖注入
 *
 * 二.自动依赖注入
 *    1。手动依赖的问题
 *         1在大型应用中，一个类中经常会存在依赖多个其他类的情况，手动依赖会存在大量样板代码
 *         2？？？当您无法在传递依赖项之前构造依赖项时（例如，在使用惰性初始化或将对象作用域确定为应用程序流时），
 *          您需要编写并维护一个自定义容器（或依赖关系图），以管理您的生命周期内存中的依赖项。（查看此包下的LoginActivity）
 *
 *    2。自动依赖能解决手动依赖的问题，提高效率。自动依赖有两种实现方式：
 *         1运行时基于反射的依赖注入（容易有性能问题）
 *         2编译时自动模仿手动依赖注入
 *
 *    3。使用类引用其他类的方式2在一定程度上可以替代依赖注入，但也会存在以下问题：
 *         1测试更多困难。不得不跟同一个全局的类提供者打交道
 *         2因为在类内直接引用，类外很难知道该类需要什么
 *         3如果还涉及到生命周期的管理，那么将更加困难
 *
 * 三.怎么选择？
 *
 * @author gyq
 * @date 2020-02-23
 */

/**
 * 1.该类直接创建所需的其他类
 *   Car和Engine是紧耦合的，不能方便的用Engine的子类去替代实现
 *   因此也不方便测试不同的Engine子类
 */
class Car {
    private val engine = Engine()

    fun start() {
        engine.start()
    }
}

class Car1 {
    private var engine:Engine? = null

    fun start() {
        engine = Engine()
        engine?.start()
    }
}

/**
 * 2.通过其他类（如工厂类等context.getSystemService()）获取。
 */
class Car2{

    private lateinit var manager: ActivityManager

    @RequiresApi(Build.VERSION_CODES.M)
    fun getSystemService(context: Context){
        manager = context.getSystemService(ActivityManager::class.java)
    }
}

/**
 * 3.通过参数形式注入
 *     1.构造函数参数
 *     2.普通函数参数
 */
class Car3 constructor(private var engine: Engine){
    fun start() {
        engine?.start()
    }

    fun setEngine(engine: Engine){
        this.engine = engine
    }
}

class Car4{
    lateinit var engine: Engine

    fun start() {
        engine.start()
    }
}

/**
 * 直接字段赋值,类似于 setter 方法
 */
fun main(args: Array<String>) {
    Car4().run {
        engine = Engine()
        start()
    }
}
