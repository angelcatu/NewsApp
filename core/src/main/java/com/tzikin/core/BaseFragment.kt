package com.tzikin.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController

/**
 * @author Angel Elias on 24/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/
abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    lateinit var binding: T
    private var navController: NavController? = null


    abstract val layoutId: Int


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.also {
            it.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController.let {
            view.findNavController()
        }
    }

    protected fun navigateTo(idDestination: Int) {
        navController?.navigate(idDestination)
    }
}