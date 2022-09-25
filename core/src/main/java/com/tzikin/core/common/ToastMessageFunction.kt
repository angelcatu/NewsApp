package com.tzikin.core.common

import android.content.Context
import android.widget.Toast

/**
 * @author Angel Elias on 25/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/

fun Context.toast(message: String){
    Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
}