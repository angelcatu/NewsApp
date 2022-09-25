package com.tzikin.presentation.view.inputs

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.tzikin.presentation.R
import com.tzikin.presentation.databinding.EmailInputTextLayoutBinding

/**
 * @author Angel Elias on 24/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/
class EmailInputText : ConstraintLayout {

    private var binding: EmailInputTextLayoutBinding =
        EmailInputTextLayoutBinding.inflate(LayoutInflater.from(context), this, false)

    constructor(context: Context) : super(context)
    constructor(context: Context, attribute: AttributeSet) : super(context, attribute){
        init()
    }
    constructor(context: Context, attribute: AttributeSet, defStyleAttr: Int) : super(
        context,
        attribute,
        defStyleAttr
    ){
        init()
    }

    private fun init() {
        addView(binding.root)
    }

    fun getEmailInput (): TextInputEditText{
        return binding.etEmail
    }
}