package com.tzikin.home.news.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tzikin.core.BaseFragment
import com.tzikin.core.common.toast
import com.tzikin.core.helpers.RequestState
import com.tzikin.home.R
import com.tzikin.home.databinding.FragmentNewsBinding
import com.tzikin.home.news.viewmodel.NewsViewModel

class NewsFragment : BaseFragment<FragmentNewsBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_news

    private val viewModel: NewsViewModel by viewModels()

    private var adapter = NewsAdapter{

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = adapter

        bindUIService()
    }

    private fun bindUIService() {
        viewModel.getNews("android", "2022-09-24", "2022-09-24")
        viewModel.requestState.observe(requireActivity()){
            when(it){
                is RequestState.loading -> {
                  showProgressBar()
                }

                is RequestState.Success -> {
                    dismissProgressBar()
                    adapter.dataHasChanged(it.value.articles)
                }

                is RequestState.Error -> {
                    dismissProgressBar()
                    requireActivity().toast( it.message)
                }
            }


        }
    }

}