package com.eclecticsIntern.newsApp.data.responseData


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val MAIN_ID = 0

@Entity(tableName = "article")
data class Article(
//    @PrimaryKey(autoGenerate = true)
//    var articleID: Int,
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    @Embedded
    val source: Source,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String,
) {
    @PrimaryKey(autoGenerate = true)
    var articleID:Int = MAIN_ID
}