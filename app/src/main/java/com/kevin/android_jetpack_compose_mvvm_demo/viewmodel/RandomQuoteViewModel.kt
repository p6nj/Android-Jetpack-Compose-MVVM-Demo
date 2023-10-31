package com.kevin.android_jetpack_compose_mvvm_demo.viewmodel


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevin.android_jetpack_compose_mvvm_demo.model.repo.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomQuoteViewModel @Inject constructor(private val quoteRepository: QuoteRepository) :
    ViewModel() {

    var quote by mutableStateOf("Click refresh")

    fun getQuotation() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = quoteRepository.getRandomQuotation()
            if (response.isSuccessful) {
                response?.body()?.let {
                    Log.e("tag", it.quote)
                    quote = it.quote
                }
            }
        }
    }

}