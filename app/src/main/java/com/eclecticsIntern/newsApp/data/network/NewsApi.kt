package com.eclecticsIntern.newsApp.data.network

import com.eclecticsIntern.newsApp.data.responseData.NewsDataResponse
import com.eclecticsIntern.newsApp.utils.APIKEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * apiKey=Is fount in local.properties
 *eg apiKey=""
 */

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("q") q: String = "tesla",
        @Query("from") from: String = "2022-09-13",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = APIKEY.toString() // "067ca509264a44c884e75e6fb44101"
    ): Response<NewsDataResponse>
}
