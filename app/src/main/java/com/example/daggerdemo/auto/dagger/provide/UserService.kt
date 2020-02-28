package com.example.daggerdemo.auto.dagger.provide

/**
 *
 * @author gyq
 * @date 2020-02-27
 */
interface UserService {
    fun getName():String
}

class UserIml :UserService{
    override fun getName():String {
        return "gyq"
    }
}