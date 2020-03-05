package com.example.daggerdemo.auto.dagger.provide.module

import com.example.daggerdemo.auto.dagger.provide.depend.Fish
import dagger.Module
import dagger.Provides

/**
 * 有参构造函数的module，并且该Module会被include到其他module中，这样组件使用了其他module时就能使用这些module(如ProvideModule)include进的module(如ParamModule)
 * @author gyq
 * @date 2020-03-05
 */
@Module
class ParamModule constructor(val name:String) {

    @Provides
    fun provideFish():Fish{
        return Fish(name)
    }
}