package com.eclecticsIntern.newsApp.ui.uiNews.home

import androidx.lifecycle.ViewModel
import com.eclecticsIntern.newsApp.data.repo.NewsRepository
import com.eclecticsIntern.newsApp.utils.lazyDeferred
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    val article by lazyDeferred {
        repository.getAllArticles()
    }

}