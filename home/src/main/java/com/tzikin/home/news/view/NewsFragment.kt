package com.tzikin.home.news.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tzikin.core.BaseFragment
import com.tzikin.core.common.toast
import com.tzikin.core.helpers.RequestState
import com.tzikin.core.repository.home.model.Articles
import com.tzikin.home.R
import com.tzikin.home.databinding.FragmentNewsBinding
import com.tzikin.home.news.viewmodel.NewsViewModel

class NewsFragment : BaseFragment<FragmentNewsBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_news

    private val viewModel: NewsViewModel by activityViewModels()

    private var adapter = NewsAdapter{
        navigateTo(NewsFragmentDirections.actionNewsFragmentToNewPageFragment(it))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        bindUIService()

    }

    private fun bindUIService() {
        viewModel.apply {


                getNews("android", "2022-09-24", "2022-09-24")
                requestState.observe(requireActivity()) {
                    when (it) {
                        is RequestState.loading -> {
                         //   showProgressBar()
                        }

                        is RequestState.Success -> {
                           // dismissProgressBar()

                            viewModel.setArticles(it.value.articles)
                            adapter.dataHasChanged(it.value.articles)

                            binding.recyclerView.adapter = adapter

                        }

                        is RequestState.Error -> {
                          //  dismissProgressBar()
                            requireActivity().toast(it.message)
                        }
                    }
                }

        }
    }

    private fun updateList(list: MutableList<Articles>) {
        adapter.dataHasChanged(list)
    }

}