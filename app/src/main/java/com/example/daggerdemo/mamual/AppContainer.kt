package com.example.daggerdemo.mamual

import retrofit2.Retrofit

/**
 *
 * @author gyq
 * @date 2020-02-24
 */
class AppContainer {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://example.com")
        .build()
        .create(LoginService::class.java)

    private val remoteDataSource = UserRemoteDataSource(retrofit)
    private val localDataSource = UserLocalDataSource()

    // userRepository is not private; it'll be exposed
    val userRepository = UserRepository(localDataSource, remoteDataSource)

    val loginViewModelFactory = LoginViewModelFactory(userRepository)

    //不同的登陆流程应该有不同的登陆数据，在一个流程开始时赋值，一个登陆流程结束时置空
    var loginContainer: LoginContainer? = null
}