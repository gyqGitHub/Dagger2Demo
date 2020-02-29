package com.example.daggerdemo.auto.dagger.provide;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;

/**
 * @author gyq
 * @date 2020-02-25
 */
@Module
public abstract class UserModule {

    /**
     * 提供实现了接口(如Interceptor)的依赖类(RequestInterceptor)
     *      1.module下 @Binds注解 和 abstract 修饰符
     *      2.方法参数为具体的实现了接口的依赖类(RequestInterceptor)，并且该依赖类一般用@Inject 注解构造方法
     *
     *  应用场景：当需要提供的依赖类是接口时，如(@Inject lateinit var interceptor:Interceptor)
     *           完全可以用@Provides代替
     *
     * @param interceptor
     * @return
     */
    @Binds
    public abstract Interceptor bindsInterceptor( RequestInterceptor interceptor);

    /**
     * @Provides跟@Binds注解实现的功能其实是类似的，不同的是@Provides适合一些没有用@Inject注解构造函数的（如第三库中类）
     * ⚠️该类中使用了@Binds，是一个抽象类，因此该类下@Provides的方法应添加static
     * @return
     */
    @Provides
    public static UserService providesUserService(){
        return new UserIml();
    }
}
