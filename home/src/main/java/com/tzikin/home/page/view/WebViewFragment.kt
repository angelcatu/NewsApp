package com.tzikin.home.page.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tzikin.core.BaseFragment
import com.tzikin.home.R
import com.tzikin.home.databinding.FragmentWebViewBinding
import com.tzikin.home.page.viewmodel.WebViewViewModel

class WebViewFragment : BaseFragment<FragmentWebViewBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_web_view

    private val viewModel: WebViewViewModel by viewModels()
    private val args: WebViewFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(!args.newURL.isEmpty()){
            binding.webView.loadUrl(args.newURL)
        }



        binding.webView.settings.javaScriptEnabled = true

    }
}