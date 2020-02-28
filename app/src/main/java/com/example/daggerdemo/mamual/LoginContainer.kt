package com.example.daggerdemo.mamual

/**
 *
 * @author gyq
 * @date 2020-02-24
 */
class LoginContainer(val userRepository: UserRepository) {

    val loginData = LoginUserData()

    val loginViewModelFactory = LoginViewModelFactory(userRepository)
}