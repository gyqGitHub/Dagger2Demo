package com.example.daggerdemo.auto.dagger.provide

import com.example.daggerdemo.auto.dagger.provide.depend.*
import com.example.daggerdemo.auto.dagger.provide.module.BindModule
import com.example.daggerdemo.auto.dagger.provide.module.ParamModule
import com.example.daggerdemo.auto.dagger.provide.module.ProvideModule
import dagger.Component

/**
 * 一。通过在接口上的@Component注解解决====>依赖哪里来，哪里去
 *      1.通过@Component注解，编译器会自动创建一个实现了该接口的和构造者模式的实现类(相当于手动创建的容器)，
 *
 *      2.==>解决依赖哪里来：该容器内的提供依赖的类型就是接口内暴露出来的函数（如getPig()）的返回值类型（Pig）
 *       （编译器自动生成的类：dagger/app/build/generated/source/kapt/debug/com/example/daggerdemo/auto/dagger/provide/DaggerProvideComponent.java）
 *         1。@Inject注解构造函数(如Pig类)
 *
 *         2。module 中 @Provides 和 @Binds 修饰的函数（如 ProvideModule 的fun providePig()）
 *
 *      3.==>解决依赖哪里去（把依赖注入到哪里去）？？？
 *        看dagger/app/src/main/java/com/example/daggerdemo/auto/dagger/inject下示例
 *
 * 二。dagger组件的创建：
 *      1。建造者模式
 *      2。静态方法create()快速创建
 *
 * 三。问题
 *      1。如果Dagger组件需要提供同一个类型的依赖，但实现不同的，比如我要提供两个String类型的值，Dagger组件怎么知道提供哪一个？？？
 *
 * @author gyq
 * @date 2020-03-05
 */
@Component(modules = [ProvideModule::class, BindModule::class])
interface ProvideComponent {
    fun getPig(): Pig

    fun getSheep(): Sheep

    fun getWolf():Wolf

    fun getAnimal():Animal

    fun getTurtle():Turtle

    fun getFish():Fish

    fun getMouse():Mouse
}

fun main() {
      //静态方法create()快速创建Dagger组件
//    DaggerProvideComponent.create().run {
//        getPig().description()
//
//        getSheep().description()
//
//        getWolf().description()
//
//        (getAnimal() as Cat).description()
//
//        getTurtle().description()
//    }

    //建造者模式创建Dagger组件，当组件需要传值时(如添加了有参构造函数的ParamModule时)一定得使用建造者模式创建
    //并且需要手动设置module，不然会报错
    DaggerProvideComponent.builder().paramModule(ParamModule("鲤鱼泡泡")).build().run {
        getPig().description()
        getSheep().description()
        getWolf().description()
        (getAnimal() as Cat).description()
        getTurtle().description()
        getFish().description()
        getMouse().description()
    }


}