package com.tzikin.core.repository.home

import com.tzikin.core.helpers.RequestState
import com.tzikin.core.repository.home.model.NewsResponse
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Angel Elias on 25/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/
interface ApiNewsInterface {
    companion object {
        private const val NEWS = "everything"
    }

    @GET(NEWS)
    suspend fun getNews(
        @Query("q") topic: String,
        @Query("from") startDate: String,
        @Query("to") endDate: String,
        @Query("pageSize") pageSize: Int = 5,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String = "0c9beae004b1417099ac4ae728f6fbb2"
    ): NewsResponse

    object NewsApi {
        val retrofitService: ApiNewsInterface by lazy {
            RetrofitClient.createNews()
        }
    }
}
