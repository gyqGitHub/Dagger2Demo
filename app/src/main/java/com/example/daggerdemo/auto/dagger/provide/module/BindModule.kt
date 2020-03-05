package com.example.daggerdemo.auto.dagger.provide.module

import com.example.daggerdemo.auto.dagger.provide.depend.Animal
import com.example.daggerdemo.auto.dagger.provide.depend.Cat
import com.example.daggerdemo.auto.dagger.provide.depend.Turtle

import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * 使用@Binds提供时，注解的方法是抽象的，module类也是抽象的；
 * 如果想在BindModule下使用@Provides注解，则它注解的方法得是静态方法
 *
 * 注意@Binds注解方法的参数(如Cat)必须是@Inject注解构造函数或者@Provides注解方法提供
 * 可以说是@Binds注解是基于@Inject和@Provides注解的
 *
 * @author gyq
 * @date 2020-03-05
 */
@Module
abstract class BindModule {
    @Binds
    abstract fun bindCat(cat: Cat): Animal

    companion object {

        @Provides
        fun provideTurtle(): Turtle {
            return Turtle()
        }
    }
}
