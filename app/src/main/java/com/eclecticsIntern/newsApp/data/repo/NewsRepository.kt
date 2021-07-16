package com.eclecticsIntern.newsApp.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eclecticsIntern.newsApp.data.db.AppDataBase
import com.eclecticsIntern.newsApp.data.db.DataPreferences
import com.eclecticsIntern.newsApp.data.network.NewsApi
import com.eclecticsIntern.newsApp.data.responseData.Article
import com.eclecticsIntern.newsApp.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class NewsRepository
    @Inject constructor(
    private val newsApi: NewsApi,
    private val appDataBase: AppDataBase,
    private val dataPreferences: DataPreferences
) :
    BaseRepository() {

    private val article = MutableLiveData<List<Article>>()

    init {
        article.observeForever {
            saveArticle(it)
        }
    }

    private fun saveArticle(list: List<Article>) {
        Coroutines.io {
            appDataBase.newsDao().upInsert(list)
            dataPreferences.saveTimeArticleFetched(LocalDateTime.now().toString())
        }
    }

    private suspend fun fetchArticles() {
        val lastSaveAt = dataPreferences.getTimeArticleWasSaved.first()
        if (lastSaveAt == null || isFetchNeeded(LocalDateTime.parse(lastSaveAt))) {
            val response = apiRequest { newsApi.getNews() }
            article.postValue(response.articles)
        }
    }

    private fun isFetchNeeded(parse: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(parse, LocalDateTime.now()) > 6
    }

    suspend fun getAllArticles(): LiveData<List<Article>> {
        return withContext(Dispatchers.IO) {
            fetchArticles()
            appDataBase.newsDao().getAllArticles()
        }
    }
}