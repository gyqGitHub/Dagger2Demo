package com.example.daggerdemo.auto.dagger.android;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;

/**
 * @author gyq
 * @date 2020-02-25
 */
@Module
public abstract class TestModule {

    @Provides
    public static Interceptor providesInterceptor(){
        return null;
    }

    /**
     * 提供实现了接口(如Interceptor)的依赖类(RequestInterceptor)
     *      1.module下 @Binds注解 和 abstract 修饰符
     *      2.方法参数为具体的实现了接口的依赖类(RequestInterceptor)，并且该依赖类一般用@Inject 注解构造方法
     * @param interceptor
     * @return
     */
    @Binds
    public abstract Interceptor bindsInterceptor( RequestInterceptor interceptor);
}
