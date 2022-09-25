package com.tzikin.home.news.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.Api
import com.tzikin.core.helpers.ApiHandleResult
import com.tzikin.core.helpers.RequestState
import com.tzikin.core.repository.home.HomeRepository
import com.tzikin.core.repository.home.model.NewsResponse
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    var homeRepository: HomeRepository = HomeRepository()
    private var _requestState: MutableLiveData<RequestState<NewsResponse>> = MutableLiveData()
    val requestState: LiveData<RequestState<NewsResponse>> = _requestState

    fun getNews(topic: String, startDate: String, endDate: String) {

        viewModelScope.launch {
            try {
                _requestState.value = RequestState.loading
                handleResponseStatus(homeRepository.getAllNews(topic, startDate, endDate))

            }catch (e: Exception) {
                _requestState.value = RequestState.Error("Error en la red")
            }

        }
    }

    private fun handleResponseStatus(requestState: RequestState<NewsResponse>) {
        _requestState.value = requestState
    }

}