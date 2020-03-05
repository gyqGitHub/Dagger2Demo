package com.example.daggerdemo.auto.dagger.provide.module

import com.example.daggerdemo.auto.dagger.provide.depend.Sheep
import com.example.daggerdemo.auto.dagger.provide.depend.Wolf
import dagger.Module
import dagger.Provides

/**
 * 想对接口/抽象类，第三方库中类进行依赖注入时，没办法使用 @Inject 注解构造方法，那么此时可通过@module来实现注入
 * 使用方法：1。在提供依赖的类上添加 @Module 注解
 *          2。在提供依赖的类的方法上添加 @Provides/@Binds 注解
 *          3。在Comment 上@Component注解上添加相应的参数(如ProvideComponent的(modules = [ProvideModule::class]))
 *
 *
 * @author gyq
 * @date 2020-03-05
 */
@Module(includes = [ParamModule::class])
class ProvideModule {

    @Provides
    fun provideSheep(): Sheep {
        return Sheep("小羊肖恩")
    }

    /**
     * 既可通过@Inject提供也可由module提供时，以module提供的为准，可看编译器根据Dagger2类注解自动生成的类
     */
    @Provides
    fun provideWolf():Wolf{
        return Wolf().apply {
            description = "我是灰太狼，通过module的@Provides注解而不是@Inject提供"
        }
    }

    @Provides
    fun provideMouseName():String{
        return "杰瑞"
    }

}