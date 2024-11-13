package com.example.weatherapplication.di

import com.example.weatherapplication.Repo
import com.example.weatherapplication.RetrofitServices
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Module {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitServices(retrofit: Retrofit) : RetrofitServices{
        return retrofit.create(RetrofitServices::class.java)
    }

    @Singleton
    @Provides
    fun provideRepo(retrofitServices: RetrofitServices) : Repo{
        return Repo(retrofitServices)
    }
}