package com.eclecticsIntern.newsApp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eclecticsIntern.newsApp.R
import com.eclecticsIntern.newsApp.data.responseData.Article
import com.eclecticsIntern.newsApp.databinding.NewItemLayoutBinding

class NewsRecyclerViewAdapter(private val article: List<Article>) :
    RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.new_item_layout,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.article = article[position]
    }

    override fun getItemCount() = article.size

    inner class ViewHolder(val binding: NewItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}