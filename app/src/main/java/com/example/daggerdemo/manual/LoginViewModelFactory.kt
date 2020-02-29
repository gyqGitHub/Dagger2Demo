package com.example.daggerdemo.manual

/**
 *
 * @author gyq
 * @date 2020-02-24
 */
// Definition of a Factory interface with a function to create objects of a type
interface Factory<T> {
    fun create(): T
}

// Factory for InjectViewModel.
// Since InjectViewModel depends on UserRepository, in order to create instances of
// InjectViewModel, you need an instance of UserRepository that you pass as a parameter.
class LoginViewModelFactory(private val userRepository: UserRepository) : Factory<LoginViewModel> {
    override fun create(): LoginViewModel {
        return LoginViewModel(userRepository)
    }
}