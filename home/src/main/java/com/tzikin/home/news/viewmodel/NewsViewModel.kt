package com.tzikin.home.news.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.tzikin.core.helpers.RequestState
import com.tzikin.core.repository.home.HomeRepository
import com.tzikin.core.repository.home.model.Articles
import com.tzikin.core.repository.home.model.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    var homeRepository: HomeRepository = HomeRepository()
    private var _requestState: MutableLiveData<RequestState<NewsResponse>> = MutableLiveData()
    val requestState: MutableLiveData<RequestState<NewsResponse>>
        get() = _requestState


    private var _articles: MutableLiveData<MutableList<Articles>> = MutableLiveData()
    val articles: LiveData<MutableList<Articles>>
        get() = _articles

    var loading = false
    var visibleItemCount: Int = 0

    fun getNews(topic: String, startDate: String, endDate: String)
    = liveData(Dispatchers.IO) {
        emit(RequestState.loading)
        try {
            emit(RequestState.Success(homeRepository.getAllNews(topic, startDate, endDate)))
        }catch (e: Exception){
            emit(RequestState.Error("Error en la red"))
        }
    }

    fun setArticlesList(value: MutableList<Articles>){
        _articles.value = value
    }

    fun addArticlesList(value: MutableList<Articles>) {
        _articles.value?.addAll(value)
    }
}