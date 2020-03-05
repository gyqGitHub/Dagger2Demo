package com.example.daggerdemo.manual

/**
 *
 * @author gyq
 * @date 2020-02-24
 */
interface Factory<T> {
    fun create(): T
}

class LoginViewModelFactory(private val userRepository: UserRepository) : Factory<LoginViewModel> {
    override fun create(): LoginViewModel {
        return LoginViewModel(userRepository)
    }
}