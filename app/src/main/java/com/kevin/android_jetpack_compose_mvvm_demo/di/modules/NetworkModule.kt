package com.kevin.android_jetpack_compose_mvvm_demo.di.modules

import com.kevin.android_jetpack_compose_mvvm_demo.di.modules.Constants.QUOTES_BASE_URL
import com.kevin.android_jetpack_compose_mvvm_demo.model.repo.api.QuoteApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return QUOTES_BASE_URL
    }

    @Provides
    @Singleton
    fun provideConverter(): Converter.Factory {
        return GsonConverterFactory.create()
    }


    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit): QuoteApiService {
        return retrofit.create(QuoteApiService::class.java)
    }


}