package com.example.daggerdemo.manual

/**
 *
 * @author gyq
 * @date 2020-02-24
 */
class UserRepository(private val localDataSource: UserLocalDataSource, private val remoteDataSource: UserRemoteDataSource)
class UserLocalDataSource
class UserRemoteDataSource(private val loginService: LoginRetrofitService)


