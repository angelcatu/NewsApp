package com.tzikin.home.news.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tzikin.core.BaseFragment
import com.tzikin.core.common.toast
import com.tzikin.core.helpers.RequestState
import com.tzikin.home.R
import com.tzikin.home.databinding.FragmentNewsBinding
import com.tzikin.home.news.viewmodel.NewsViewModel
import java.text.SimpleDateFormat
import java.util.*

class NewsFragment : BaseFragment<FragmentNewsBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_news

    private val viewModel: NewsViewModel by activityViewModels()

    private var adapter = NewsAdapter {
        navigateTo(NewsFragmentDirections.actionNewsFragmentToNewPageFragment(it))
    }

    lateinit var mLayoutManager: LinearLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mLayoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.layoutManager = mLayoutManager
        bindUIService(isReload = false)


        onScrollListener()
    }

    private fun onScrollListener() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    viewModel.visibleItemCount = mLayoutManager.childCount
                    if (!viewModel.loading) {
                        if (mLayoutManager.findLastCompletelyVisibleItemPosition() == viewModel.articles.value?.size!! - 1) {
                            bindUIService(true)
                            viewModel.loading = false
                        }
                    }
                }
            }
        })

    }

    private fun bindUIService(isReload: Boolean) {
        val c = Calendar.getInstance().time
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        viewModel.getNews("android", date.format(c), date.format(c)).observe(requireActivity()) {
            it?.let {
                when (it) {
                    is RequestState.loading -> {
                        showProgressBar()
                    }

                    is RequestState.Success -> {
                        dismissProgressBar()
                        if (!isReload) {
                            // ADDING NEWS
                            viewModel.setArticlesList(it.value.articles)
                            adapter.dataHasChanged(it.value.articles)
                        } else {
                            // RELOADING NEWS
                            val entryListSize = it.value.articles.size
                            viewModel.addArticlesList(it.value.articles)
                            adapter.dataHasInserted(it.value.articles)
                            val listSize = viewModel.articles.value?.size
                            if (listSize != null) {
                                scrollToPosition(listSize - entryListSize - 2)
                            }
                        }
                        binding.recyclerView.adapter = adapter
                    }

                    is RequestState.Error -> {
                        dismissProgressBar()
                        requireActivity().toast(it.message)
                    }
                }
            }
        }
    }

    private fun scrollToPosition(position: Int) {
        binding.recyclerView.scrollToPosition(position)
    }

}