package com.tzikin.core.repository.home

import com.tzikin.core.helpers.ApiHandleResult
import com.tzikin.core.helpers.RequestState
import com.tzikin.core.repository.home.ApiNewsInterface.NewsApi.retrofitService
import com.tzikin.core.repository.home.model.NewsResponse
import com.tzikin.core.repository.makeNetworkCall

/**
 * @author Angel Elias on 25/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/
class HomeRepository {

    suspend fun getAllNews(topic: String, startDate: String, endDate: String): RequestState<NewsResponse>
    = makeNetworkCall {
        retrofitService.getNews(topic, startDate, endDate)
    }

}