package com.kevin.android_jetpack_compose_mvvm_demo.viewmodel


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevin.android_jetpack_compose_mvvm_demo.model.repo.QuoteRepository
import com.kevin.androidmvvmdemo.model.data.AnimeQuotation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RandomQuoteViewModel @Inject constructor(private val quoteRepository: QuoteRepository) :
    ViewModel() {

    var quote by mutableStateOf("Click refresh")

    private val _quotes = MutableLiveData<List<AnimeQuotation>>()
    val quotes: LiveData<List<AnimeQuotation>>
        get() = _quotes

    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error


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

    fun getMultipleQuotations() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = quoteRepository.getMultipleQuotations()
                if (response.isSuccessful) {
                    _quotes.postValue(response.body())
                } else {
                    _error.postValue("Something went wrong")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _error.postValue(e.message)
            }
        }
    }

}