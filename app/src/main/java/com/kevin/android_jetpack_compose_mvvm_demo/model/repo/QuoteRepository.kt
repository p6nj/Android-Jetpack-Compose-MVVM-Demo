package com.kevin.android_jetpack_compose_mvvm_demo.model.repo

import com.kevin.android_jetpack_compose_mvvm_demo.model.repo.api.QuoteApiService
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val quoteApiService: QuoteApiService) :
    IQuoteRepository {
    override suspend fun getRandomQuotation() = quoteApiService.getRandomQuotation()
}