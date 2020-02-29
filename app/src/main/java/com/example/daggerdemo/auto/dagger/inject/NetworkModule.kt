package com.example.daggerdemo.auto.dagger.inject

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * 解决：想对接口，第三方类依赖注入或者需要根据运行结果决定创建某个类时，没办法使用 @Inject 注解构造方法的情况
 *      (协助 Comment 组件解决依赖哪里来)
 *
 *      会根据该类中的方法(provideLoginRetrofitService())生成工厂类(NetworkModule_ProvideLoginRetrofitServiceFactory)
 *
 * 使用：1。在提供依赖的类上添加 @Module 注解
 *      2。在提供依赖的类的方法上添加 @Provides 注解
 *      3。在Comment 上@Component(modules = [NetworkModule::class])
 *
 *  如果增加参数okHttpClient: OkHttpClient，该怎么处理呢？
 *
 *  module可以用在 Component 中，也可以用在其他的 module 上，是个强大功能，但容易滥用，因为一旦添加到module到组件中，Dagger就能通过
 *  组件去提供依赖类，因此在添加之前需要先确定该module是否已添加，避免重复添加，造成性能的损失
 *  良好的做法是：任意两个或多个module中公共的部分抽出来组成一个新的module
 *
 *  *module可以在组件的Builder中手动传入
 *
 * @author gyq
 * @date 2020-02-24
 */
@Module
class NetworkModule {

    @Provides
    internal fun provideLoginService(): InjectService {
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .build()
            .create(InjectService::class.java)
    }
}
