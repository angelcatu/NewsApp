package com.tzikin.presentation.view.buttons

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.tzikin.presentation.databinding.BaseButtonLayoutBinding

/**
 * @author Angel Elias on 24/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/
class BaseButton : ConstraintLayout {

    private var binding: BaseButtonLayoutBinding =
        BaseButtonLayoutBinding.inflate(LayoutInflater.from(context), this, false)

    constructor(context: Context) : super(context)


    constructor(context: Context, attribute: AttributeSet) : super(context, attribute){
        init(context, attribute)
    }


    constructor(context: Context, attribute: AttributeSet, defStyleAttr: Int) : super(
        context,
        attribute,
        defStyleAttr
    ){
        init(context, attribute)
    }

    fun onClickListener(onClickListener: () -> Unit) {
        binding.baseButton.setOnClickListener { onClickListener.invoke() }
    }

    fun setText(value: String){
        binding.baseButton.text = value
    }

    private fun init(context: Context, attribute: AttributeSet) {
        addView(binding.root)

        val attrs = context.theme.obtainStyledAttributes(
            attribute,
            com.tzikin.core.R.styleable.baseButton,
            0, 0
        )
        val arryRefR = attrs.getString(com.tzikin.core.R.styleable.baseButton_text)

        if(!arryRefR.isNullOrEmpty()){
            setText(arryRefR)
        }

        attrs.recycle()
    }

}