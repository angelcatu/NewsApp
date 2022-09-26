package com.tzikin.home.page.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.tzikin.core.BaseFragment
import com.tzikin.home.R
import com.tzikin.home.databinding.FragmentNewPageLayoutBinding
import com.tzikin.home.databinding.FragmentNewsBinding
import com.tzikin.home.news.viewmodel.NewsViewModel
import com.tzikin.home.page.viewmodel.NewPageViewModel

class NewPageFragment : BaseFragment<FragmentNewPageLayoutBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_new_page_layout

    private val viewModel: NewPageViewModel by viewModels()

    private val args: NewPageFragmentArgs by navArgs()
    private val homeViewModel : NewsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bindUI()

        binding.txtPageMoreInformation.setOnClickListener{
            val article = homeViewModel.articles.value?.get(args.newsId)
            navigateTo(NewPageFragmentDirections.actionNewPageFragmentToWebViewFragment(article?.url.toString()))
        }

    }

    private fun bindUI() {

        val article = homeViewModel.articles.value?.get(args.newsId)

        binding.txtPageTitle.text = article?.title
        binding.txtPageContent.text = article?.content
        binding.txtPageDate.text = article?.publishedAt
        binding.txtPageSource.text = article?.source?.name
        binding.txtPageDescription.text = article?.description
        Picasso.get().load(article?.urlToImage).into(binding.imgPageNew)
    }

}