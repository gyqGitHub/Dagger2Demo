package com.example.daggerdemo.auto.dagger.inject

import dagger.Component

@Component(modules = [NetworkModule::class])
interface InjectComponent {
    //只要有了相应的inject方法，那么就不需要在接口中定义方法获取依赖类了；
    // 有了inject方法会自动地把依赖注入到该方法中的参数的类型中
    //一般像在ApplicationComponent才会通过在接口中定义方法提供依赖类，因为ApplicationComponent是全局的，生命周期同应用程序，可能会在多处地方用到
    fun inject(activity: InjectActivity)
}