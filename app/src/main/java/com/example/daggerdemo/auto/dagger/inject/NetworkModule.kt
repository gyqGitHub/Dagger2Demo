package com.example.daggerdemo.auto.dagger.inject

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
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
