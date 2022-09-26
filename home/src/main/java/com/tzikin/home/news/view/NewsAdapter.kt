package com.tzikin.home.news.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tzikin.core.repository.home.model.Articles

import com.tzikin.home.databinding.NewsListLayoutBinding

/**
 * @author Angel Elias on 25/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/
class NewsAdapter(private var newsList: MutableList<Articles> = mutableListOf(), private val onClickListener: (Articles) -> Unit): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position], onClickListener)
    }

    override fun getItemCount() = newsList.size

    class ViewHolder(var binding: NewsListLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(article: Articles, onClickListener: (Articles) -> Unit) {
            binding.txtTitle.text = article.title
            binding.txtDescription.text = article.description
            binding.txtAuthor.text = article.author
            binding.txtDate.text = article.publishedAt

            Picasso.get().load(article.urlToImage).into(binding.imgNew)

            binding.arrow.setOnClickListener{
                onClickListener.invoke(article)
            }
    }}

    fun dataHasChanged(list: MutableList<Articles>) {
        this.newsList.clear()
        this.newsList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }
}