package com.tzikin.home.news.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tzikin.core.repository.home.model.Articles

import com.tzikin.home.databinding.NewsListLayoutBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Angel Elias on 25/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/
class NewsAdapter(private var newsList: MutableList<Articles> = mutableListOf(), private val onClickListener: (Int) -> Unit): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position], onClickListener)
    }

    override fun getItemCount() = newsList.size

    class ViewHolder(var binding: NewsListLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(article: Articles, onClickListener: (Int) -> Unit) {
            binding.txtTitle.text = article.title
            binding.txtDescription.text = article.description
            binding.txtAuthor.text = article.source.name


            val date = article.publishedAt
            val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val finalFormat = SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault())

            val formatted = originalFormat.parse(date)
            val result = formatted?.let { finalFormat.format(it) }
            binding.txtDate.text = result

            Picasso.get().load(article.urlToImage).resize(350, 180).centerCrop()
                .into(binding.imgNew)

            binding.arrow.setOnClickListener{
                onClickListener.invoke(layoutPosition)
            }
    }}

    fun dataHasChanged(list: MutableList<Articles>) {
        this.newsList.clear()
        this.newsList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

    fun dataHasInserted(list: MutableList<Articles>) {
        this.newsList.addAll(list)
        notifyItemInserted(this.newsList.size - 1)
    }
}