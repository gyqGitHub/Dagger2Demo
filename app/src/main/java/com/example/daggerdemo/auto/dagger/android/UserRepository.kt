package com.example.daggerdemo.auto.dagger.android

import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author gyq
 * @date 2020-02-24
 */
@Singleton
class UserRepository @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
)

class UserLocalDataSource @Inject constructor()

/**
 * LoginRetrofitService 是一个接口，并不能直接通过 new 的方式创建
 */
class UserRemoteDataSource @Inject constructor(private val loginService: LoginRetrofitService)