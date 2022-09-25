package com.tzikin.core.helpers

/**
 * @author Angel Elias on 24/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/
sealed class ApiHandleResult<out T>(
    val data: T? = null,
    val message: String? = null,
    val code: String? = null
) {
    data class Success<out T>(val value: T) : ApiHandleResult<T>(data = value)
    data class ApiError(val httpCode: String? = null, val error: String) :
        ApiHandleResult<Nothing>(code = httpCode, message = error)
    data class NetworkError(val errorNetworkMessage: String) :
        ApiHandleResult<Nothing>(message = errorNetworkMessage)
}