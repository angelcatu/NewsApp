package com.tzikin.core.repository.home

import com.tzikin.core.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Angel Elias on 25/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/
object RetrofitClient {

    fun createNews(): ApiNewsInterface{

        var mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        var mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiNewsInterface::class.java)
    }
}