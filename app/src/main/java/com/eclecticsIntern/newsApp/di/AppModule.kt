package com.eclecticsIntern.newsApp.di

import android.content.Context
import com.eclecticsIntern.newsApp.data.db.AppDataBase
import com.eclecticsIntern.newsApp.data.db.DataPreferences
import com.eclecticsIntern.newsApp.data.network.NetworkInterceptor
import com.eclecticsIntern.newsApp.data.network.NewsApi
import com.eclecticsIntern.newsApp.data.network.RemoteDataSource
import com.eclecticsIntern.newsApp.data.repo.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun providesNetworkInterceptor(@ApplicationContext context: Context): NetworkInterceptor {
        return NetworkInterceptor(context)
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(
        @ApplicationContext context: Context,
        networkInterceptor: NetworkInterceptor
    ): RemoteDataSource {
        return RemoteDataSource(context, networkInterceptor)
    }

    @Provides
    @Singleton
    fun providesNewsApi(remoteDataSource: RemoteDataSource): NewsApi {
        return remoteDataSource.buildApi(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesDataPreference(@ApplicationContext context: Context): DataPreferences {
        return DataPreferences(context)
    }

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDataBase {
        return AppDataBase(context)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(
        newsApi: NewsApi,
        appDataBase: AppDataBase,
        dataPreferences: DataPreferences
    ): NewsRepository {
        return NewsRepository(newsApi, appDataBase, dataPreferences)
    }


}