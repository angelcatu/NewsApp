package com.tzikin.core.repository.home.model

/**
 * @author Angel Elias on 25/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: MutableList<Articles>
)
data class Articles(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

data class Source(
    val id: String?,
    val name: String
)