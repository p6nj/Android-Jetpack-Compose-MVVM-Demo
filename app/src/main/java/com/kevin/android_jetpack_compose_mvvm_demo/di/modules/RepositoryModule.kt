package com.kevin.android_jetpack_compose_mvvm_demo.di.modules

import com.kevin.android_jetpack_compose_mvvm_demo.model.repo.IQuoteRepository
import com.kevin.android_jetpack_compose_mvvm_demo.model.repo.QuoteRepository
import com.kevin.android_jetpack_compose_mvvm_demo.model.repo.api.QuoteApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(quoteApiService: QuoteApiService): IQuoteRepository {
        return QuoteRepository(quoteApiService)
    }
}