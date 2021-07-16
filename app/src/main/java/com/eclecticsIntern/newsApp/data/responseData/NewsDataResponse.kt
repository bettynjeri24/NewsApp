package com.eclecticsIntern.newsApp.data.responseData


import com.google.gson.annotations.SerializedName

data class NewsDataResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)