package com.eclecticsIntern.newsApp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eclecticsIntern.newsApp.data.responseData.Article

@Dao
interface NewDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upInsert( article: List<Article>)

    @Query("SELECT * FROM article")
    fun getAllArticles(): LiveData<List<Article>>


}