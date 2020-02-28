package com.example.daggerdemo.auto.dagger.android

import javax.inject.Inject

/**
 * 构造函数新增一个参数：private val userRepository: UserRepository ？？？
 * 这个时候 LoginComponent 没有办法去获取UserRepository依赖，但是 ApplicationComponent 是可以的，
 * 那么使用在 LoginComponent 上使用@Subcomponent注解，即可使用 ApplicationComponent 中能够获取的依赖
 * @author gyq
 * @date 2020-02-24
 */
@ActivityScope
class LoginViewModel @Inject constructor(private val userRepository: UserRepository)