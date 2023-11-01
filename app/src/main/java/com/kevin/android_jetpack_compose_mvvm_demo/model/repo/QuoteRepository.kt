package com.kevin.android_jetpack_compose_mvvm_demo.model.repo

import com.kevin.android_jetpack_compose_mvvm_demo.model.repo.api.QuoteApiService
import com.kevin.androidmvvmdemo.model.data.AnimeQuotation
import retrofit2.Response
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val quoteApiService: QuoteApiService) :
    IQuoteRepository {
    override suspend fun getRandomQuotation() = quoteApiService.getRandomQuotation()

    override suspend fun getMultipleQuotations() = quoteApiService.getMultipleQuotations()
}