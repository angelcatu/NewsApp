package com.tzikin.core.common

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.tzikin.core.R
import com.tzikin.core.databinding.ProgressBarLayoutBinding

/**
 * @author Angel Elias on 25/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/
class ProgressBar: DialogFragment() {

    lateinit var binding: ProgressBarLayoutBinding

    override fun onResume() {
        super.onResume()

        // Setting custom dimensions
        val width = 500
        val height = 500

        dialog?.window?.setLayout(width, height)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it, R.style.ProgressBarDialogStyle)
            binding = ProgressBarLayoutBinding.inflate(layoutInflater)
            builder.setView(binding.root)
            isCancelable = false
            builder.create()
        } ?: throw IllegalStateException("Activity cannot null")
    }
}