package com.kevin.android_jetpack_compose_mvvm_demo.model.repo

import com.kevin.androidmvvmdemo.model.data.AnimeQuotation
import retrofit2.Response

interface IQuoteRepository {
    suspend fun getRandomQuotation(): Response<AnimeQuotation>

    suspend fun getMultipleQuotations(): Response<List<AnimeQuotation>>
}