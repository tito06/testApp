package com.example.mytestapplication.di

import com.example.mytestapplication.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {


    @Singleton
    @Provides
    fun provideLoginInterceptor(): HttpLoggingInterceptor{
        return  HttpLoggingInterceptor().apply {
            HttpLoggingInterceptor.Level.BODY
        }
    }


    @Singleton
    @Provides
    fun provideHttpClient(
        provideLoggingInterceptor: HttpLoggingInterceptor
    ):OkHttpClient{
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(provideLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConvertor():GsonConverterFactory{
        return  GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ):Retrofit{

        return Retrofit.Builder()
            .baseUrl("https://navkiraninfotech.com/g-mee-api/api/v1/apps/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ApiService {
        return  retrofit.create(ApiService::class.java)
    }




}