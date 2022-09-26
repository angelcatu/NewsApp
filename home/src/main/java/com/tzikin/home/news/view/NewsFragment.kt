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
        viewModel.getNews("android", "2022-09-25", "2022-09-25")
        viewModel.apply {
                requestState.observe(requireActivity()) {
                    when (it) {
                        is RequestState.loading -> {
                            //showProgressBar()
                        }

                        is RequestState.Success -> {


                            viewModel.setArticles(it.value.articles)
                            adapter.dataHasChanged(it.value.articles)

                            binding.recyclerView.adapter = adapter
                            //dismissProgressBar()
                        }

                        is RequestState.Error -> {

                            requireActivity().toast(it.message)
                            //dismissProgressBar()
                        }
                    }
                }

        }
    }

    private fun updateList(list: MutableList<Articles>) {
        adapter.dataHasChanged(list)
    }

}