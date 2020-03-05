package com.example.daggerdemo.auto.dagger.inject

import javax.inject.Inject

/**
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