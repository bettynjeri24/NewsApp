package com.eclecticsIntern.newsApp.data.db

import android.content.Context
import android.content.Entity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eclecticsIntern.newsApp.data.responseData.Article
import java.math.MathContext

@Database(entities = [Article::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun newsDao(): NewDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(Any()) {
            instance ?: buildRoomDatabase(context).also {
                instance = it
            }
        }

        private fun buildRoomDatabase(context: Context) =
            Room.databaseBuilder(context, AppDataBase::class.java, "NewsDb").build()
    }

}