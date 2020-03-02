package com.example.daggerdemo.auto.dagger.provide

import android.util.Log
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * 解决：想对接口，第三方类依赖注入或者需要根据运行结果决定创建某个类时，没办法使用 @Inject 注解构造方法的情况===>解决依赖哪里来
 *      会根据该类中的方法(provideLoginRetrofitService())生成工厂类(NetworkModule_ProvideLoginRetrofitServiceFactory),该工厂类持有
 *      module(如 NetworkModule)对应的字段，最终依赖的提供者就是module
 *
 * 使用：1。在提供依赖的类上添加 @Module 注解
 *      2。在提供依赖的类的方法上添加 @Provides 注解
 *      3。在Comment 上@Component(modules = [NetworkModule::class])
 *
 *  如果增加参数okHttpClient: OkHttpClient，该怎么处理呢？
 *
 *  module可以用在 Component 中，也可以用在其他的 module 上，是个强大功能，但容易滥用，因为一旦添加module到组件中，Dagger就能通过
 *  组件去提供依赖类，因此在添加之前需要先确定该module是否已添加，避免重复添加，造成性能的损失
 *  良好的做法是：任意两个或多个module中公共的部分抽出来组成一个新的module
 *
 *  *module可以在组件(如DaggerApplicationGraph)的Builder中手动传入，意味着module可以有自己的字段和构造函数
 *
 *  总结：1.编译器生成的Dagger组件(如DaggerApplicationGraph)中module可以由用户手动传入
 *       (如果module没有声明构造方法，默认使用无参构造方法，如果声明了有参构造方法，则必须由用户手动创建module并传入，否则会报异常；
 *       并且Dagger组件将不会再有create()快捷创建方法，必须使用Builder创建，所以如果module中需要用到的数据不能自动创建，如基本数据类型，则可以通过module构造方法传入)
 *
 *       2.类似于@Inject提供依赖，作用域函数不会影响module对应方法工厂类(如NetworkModule_ProvideLoginRetrofitService$app_debugFactory)的生成;
 *         但会影响Dagger组件的生成
 * @author gyq
 * @date 2020-02-24
 */
@Module(includes = [MultiModule::class])
class NetworkModule constructor(private val test:String){

    @Provides
    internal fun provideLoginRetrofitService(multiData: MultiData): LoginRetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .build()
            .create(LoginService::class.java)
    }

    @Provides
    internal fun provideLoginService(): LoginService {
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .build()
            .create(LoginService::class.java)
    }
}
