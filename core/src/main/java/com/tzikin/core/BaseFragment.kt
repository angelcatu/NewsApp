package com.tzikin.core

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.tzikin.core.ui.AppFunctions

/**
 * @author Angel Elias on 24/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/
abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    lateinit var binding: T
    private lateinit var navController: NavController


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

        navController = view.findNavController()
    }

    protected fun navigateTo(idDestination: Int) {
        navController.navigate(idDestination)
    }

    protected fun navigateTo(deeplinkString: String) {
        try {
            val request = NavDeepLinkRequest.Builder
                .fromUri(deeplinkString.toUri())
                .build()
            findNavController().navigate(request)
        } catch (e: Exception) {
            Log.d("Navigation exception: ", e.message.toString())
        }
    }

    protected fun showProgressBar() {
        (requireActivity() as AppFunctions).showProgressBar()
    }

    protected fun dismissProgressBar() {
        (requireActivity() as AppFunctions).hideProgressBar()
    }
}