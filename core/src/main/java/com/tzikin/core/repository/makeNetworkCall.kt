package com.tzikin.core.repository

import com.tzikin.core.helpers.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Angel Elias on 25/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/
suspend fun <T> makeNetworkCall(
    call: suspend () -> T
): RequestState<T> {
    return withContext(Dispatchers.IO) {
        try {
            RequestState.Success(call())
        }catch (e: Exception) {
            RequestState.Error("Error")
        }
    }
}