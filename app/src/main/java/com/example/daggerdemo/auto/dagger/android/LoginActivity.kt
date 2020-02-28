package com.example.daggerdemo.auto.dagger.android

import android.app.Activity
import android.os.Bundle
import okhttp3.Interceptor
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 通过 @Inject 注解字段进行依赖注入（协助 Comment 组件解决依赖哪里去的问题）
 *     1。首先通过 @Inject 注解字段（loginViewModel）告诉 dagger 需要注入依赖，并自动生成一个 MembersInjector 类（
 *        如LoginActivity_MembersInjector，该类通过 injectLoginViewModel() 来注入LoginViewModel的
 *
 *     2。其次还要通过在 comment 组件中的inject()方法注入 @Inject 注解的字段(loginViewModel)所在类来告诉 dagger
 *        @Inject 注解的字段所在类(如LoginActivity)（依赖哪里去）
 *
 * 注意：1。在 Activity 中使用时，需要在 onCreate() 中的 super.onCreate(savedInstanceState) 调用之前
 *      2。在 Fragment 中使用时，需要在 onAttach() 中使用，在 super.onAttach() 调用之前之后都可以
 *
 *
 *
 *  dagger2 最佳做法：
 *      1。ApplicationComponent 一定是在app module 中的
 *      2。当要执行字段注入或者针对某个流程作用域时，创建相应的组件
 *      3。？？？https://developer.android.google.cn/training/dependency-injection/dagger-multi-module
 *         对于一些工具辅助的模块，一般不需要创建组件，只需要创建用@Provides 和 @Binds 提供无法用注解构造方法注入的依赖类的dagger Module
 *      4。对于多module项目，使用dependencies 访问app module中的ApplicationComponent
 *
 * @author gyq
 * @date 2020-02-24
 */
class LoginActivity: Activity() {
    // You want Dagger to provide an instance of LoginViewModel from the graph
    //注解的可访问性修饰符不能是private
    @Inject
    lateinit var loginViewModel: LoginViewModel

    lateinit var loginComponent: LoginComponent

//    @Inject
//    lateinit var interceptor: Interceptor

    override fun onCreate(savedInstanceState: Bundle?) {
//        DaggerApplicationComponent.create().inject(this)
        (applicationContext as MyApplication).appComponent.run {
//            this.repository()
            loginComponent = this.loginComponent().create().apply {
//                inject(this@LoginActivity)
            }
        }
//        DaggerLoginComponent.create().inject(this)
        super.onCreate(savedInstanceState)

    }
}
