package com.example.daggerdemo.auto.dagger.inject

import javax.inject.Inject

/**
 *
 * 作用域注解出现位置：1。@Inject构造函数，@Binds和@Provides上面
 *                   2。Comment组件(ApplicationGraph)的上面
 * @author gyq
 * @date 2020-02-24
 */
class UserRepository @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
)

class UserLocalDataSource @Inject constructor()
class UserRemoteDataSource @Inject constructor()

class UserData @Inject constructor(){
    var name = "gyq"
}