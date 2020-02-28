package com.example.daggerdemo.auto.dagger.android

import dagger.Subcomponent
import javax.inject.Scope

/**
 * 登陆流程的 Comment 组件的生命周期应该是与 LoginActivity 相关联的
 *
 * 分析：因为 LoginActivity 注入的依赖 LoginViewModel 的创建需要用到 UserRepository
 * 这个时候的 LoginComponent 没有办法去获取 UserRepository 依赖，但是 ApplicationComponent 是可以的，
 *
 * 1。在 LoginComponent 上使用@Subcomponent注解，并关联 ApplicationComponent。
 *      1使用@Subcomponent.Factory 告诉 ApplicationComponent 怎么创建 LoginComponent
 *      2创建关联LoginComponent的 Module（SubcomponentsModule） 并在 ApplicationComponent 的@Comment注解的 modules 参数中配置该module
 *      3在 ApplicationComponent 创建获取 LoginComponent.Factory 的方法
 *
 * 2。LoginComponent 在流程范围内，每次提供的 LoginViewModel 实例应该是同一个,但明显不能用 @Singleton 注解，因为父组件也使用了 @Singleton
 *   这样会把LoginViewModel的生命周期变成app生命周期，那么怎么解决依赖作用域的问题呢？
 *
 *   ！！！！那就是自定义作用域注解！！！！
 *   作用域规则：·当依赖类被作用域注解时，该依赖类只能被具有相同作用域的组件所使用
 *              ·当组件被作用域注解时，不管依赖类有没有被作用域注解，都能提供
 *              ·子组件不能使用父组件使用过的作用域注解
 *
 *              组件的意思也可以是子组件
 *              同一个作用域内的同一个组件，每次提供的依赖类是同一个，而不是每次重新创建
 *
 * @author gyq
 * @date 2020-02-24
 */

/**
 * 自定义作用域
 * dagger/app/build/generated/source/kapt/debug/com/example/daggerdemo/auto/dagger/android/DaggerApplicationComponent.java
 * 下可以看到 loginViewModelProvider 已经跑到了DaggerApplicationComponent中的内部类 LoginComponentImpl 中了，
 *  这里体现了作用域规则的第一条
 *
 * 在使用作用域之前,如果子组件(LoginComponent)和父组件(ApplicationComponent)同时具有注入方法(inject(activity: LoginActivity))，
 * 那么依赖类(LoginViewModel)最终是由父组件提供的，使用了作用域的话就要受到作用域的限制了
 */
@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope

@ActivityScope
@Subcomponent
interface LoginComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(activity: LoginActivity)
    fun inject(usernameFragment: LoginUsernameFragment)
    fun inject(passwordFragment: LoginPasswordFragment)
}