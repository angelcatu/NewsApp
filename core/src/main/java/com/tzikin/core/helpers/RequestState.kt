package com.tzikin.core.helpers

/**
 * @author Angel Elias on 24/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/
sealed class RequestState<out T> {
    data class Success<out T>(val value: T): RequestState<T>()
    data class Error(val message: String): RequestState<Nothing>()
    object loading: RequestState<Nothing>()
}