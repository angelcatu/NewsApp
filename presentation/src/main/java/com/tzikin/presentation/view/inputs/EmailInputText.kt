package com.tzikin.presentation.view.inputs

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.tzikin.presentation.databinding.EmailInputTextLayoutBinding

/**
 * @author Angel Elias on 24/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/
class EmailInputText : ConstraintLayout {

    private var binding: EmailInputTextLayoutBinding =
        EmailInputTextLayoutBinding.inflate(LayoutInflater.from(context), this, false)

    constructor(context: Context) : super(context)
    constructor(context: Context, attribute: AttributeSet) : super(context, attribute)
    constructor(context: Context, attribute: AttributeSet, defStyleAttr: Int) : super(
        context,
        attribute,
        defStyleAttr
    )

    init {
        addView(binding.root)
    }
}