package com.example.daggerdemo.auto.dagger.provide

import javax.inject.Inject
import javax.inject.Singleton

/**
 * 通过@Inject注解构造方法进行依赖注入，自动生成提供该依赖类的工厂类(协助 Component 解决依赖从哪里来)
 * 1。如果构造函数(如 UserRemoteDataSource 的构造函数)没有参数，那么自动生成提供该依赖类的工厂类(如 UserRemoteDataSource_Factory )是一个单例类
 *    但每次提供的依赖类是新创建的
 *
 * 2。如果构造函数(如 UserTest 的构造函数)有参数，那么自动生成提供该依赖类的工厂类(如 UserRemoteDataSource_Factory )是一个普通的工厂类。
 *    该工厂类持有一个提供构造函数参数依赖的字段(如 nameProvider)
 *
 * 意义：通过@Inject注解告诉 Dagger 如何创建一个UserRepository实例；如果@Inject注解的构造函数有参数，那么还要告诉Dagger怎么创建这些参数。
 *      (告诉的方式有两种：一种是现在说的@Inject，另一种是通过Module(如 NetworkModule)的方式提供)
 *
 *
 * 作用域注解出现位置：1。需要限定范围的类(UserRepository)的上面(还有就是Module中提供具体依赖方法的上面(如provideLoginRetrofitService()))
 *                   2。Comment组件(ApplicationGraph)的上面
 *
 * @author gyq
 * @date 2020-02-24
 */
@Singleton
class UserRepository @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
)

class UserLocalDataSource @Inject constructor()
class UserRemoteDataSource @Inject constructor()
class UserTest @Inject constructor(private val name:String)