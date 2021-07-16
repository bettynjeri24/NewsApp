package com.eclecticsIntern.newsApp.data.network

import com.eclecticsIntern.newsApp.data.responseData.NewsDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("q") q: String = "Apple",
        @Query("from") from: String = "2021-07-15",
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = "067ca509264a44c884e75e6fb4410150",
    ): Response<NewsDataResponse>
}