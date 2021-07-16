package com.eclecticsIntern.newsApp.data.network

import android.content.Context
import com.eclecticsIntern.newsApp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource(context: Context,private val networkInterceptor: NetworkInterceptor) {
    private val BASE_URL = "https://newsapi.org/v2/"
    fun <Api> buildApi(api: Class<Api>): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient(networkInterceptor))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

    private fun getOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient().newBuilder().apply {
            if (BuildConfig.DEBUG) {
                val loggingInterceptor =
                    HttpLoggingInterceptor().setLevel(level = HttpLoggingInterceptor.Level.BODY)
                addInterceptor(loggingInterceptor)
            }
            addInterceptor(interceptor)
            addInterceptor { chain ->
                chain.proceed(chain.request()).newBuilder().also {
                    it.addHeader("Accept", "application/json")
                }.build()
            }
        }.build()
    }
}