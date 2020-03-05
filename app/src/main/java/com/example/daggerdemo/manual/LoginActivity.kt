package com.example.daggerdemo.manual

import android.app.Activity
import android.os.Bundle

/**
 * 1。要创建一个LoginViewModel，需要先创建UserRepository，而UserRepository又需要UserRemoteDataSource和UserLocalDataSource实例，
 * UserRemoteDataSource的创建又需要先创建Retrofit
 *
 * 这种依赖方式有三个问题
 *     1想在其他类中再创建新的一个LoginViewModel，需要写一大堆重复的样板代码
 *
 *     2依赖必须按顺序声明，如创建LoginViewModel之前必须先创建UserRepository
 *
 *     3很难重用代码。如UserRepository，一般需要通过单例模式来实现代码重用，单例也会让测试更困难
 *      解决方法：把重复的样板代码封装到一个类中达到复用，如AppContainer；封装到Application中，因此也不需要单例类了
 *
 *               如果在多个地方（如多个Activity）使用到了LoginViewModel，则可以通过LoginViewModelFactory创建LoginViewModel
 *               甚至可以放在如AppContainer中，供app全局范围使用,这种方式比前一种更好，但仍有两个问题：
 *                  1需要管理AppContainer，并且该类提供的其他类实现还是要手动创建
 *                  2仍会有模板代码的存在
 *
 *  2。在程序流程中管理依赖类的生命周期：如不同账户的LoginUserData应该是不同的，应该在登陆流程开始时赋值，登陆流程结束置空
 *
 *  3。结论：手动依赖的方式：通过容器共享类实例和使用工厂方法集中类创建时的位置
 *                        面临两大问题：
 *                          1大量样板代码（如工厂方法创建实例）
 *                          2容器的范围和生命周期的管理（如登陆流程）
 *
 * @author gyq
 * @date 2020-02-24
 */
class LoginActivity: Activity() {

    private lateinit var loginData: LoginUserData
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://example.com")
//            .build()
//            .create(ScopeService::class.java)

//        val remoteDataSource = UserRemoteDataSource(retrofit)
//        val localDataSource = UserLocalDataSource()

//        val userRepository = UserRepository(localDataSource, remoteDataSource)

//        scopeViewModel = ScopeViewModel(userRepository)

        //把重复的样板代码封装到一个类中达到复用，如AppContainer；封装到Application中，因此也不需要单例类了
//        scopeViewModel = ScopeViewModel((applicationContext as MyApplication).appContainer.userRepository)

          //如果在多个地方（如多个Activity）使用到了LoginViewModel，则可以通过LoginViewModelFactory创建LoginViewModel
//        scopeViewModel = (applicationContext as MyApplication).appContainer.loginViewModelFactory.create()

        //在程序流程中管理依赖
        appContainer = (applicationContext as MyApplication).appContainer
        appContainer.loginContainer = LoginContainer(appContainer.userRepository)
        appContainer.loginContainer?.run {
            loginViewModel = loginViewModelFactory.create()
        }
        loginData = appContainer.loginContainer!!.loginData


    }

    override fun onDestroy() {
        super.onDestroy()
        appContainer.loginContainer = null
    }
}