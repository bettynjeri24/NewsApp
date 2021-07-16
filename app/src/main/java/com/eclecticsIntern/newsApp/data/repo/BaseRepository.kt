package com.eclecticsIntern.newsApp.data.repo

import com.eclecticsIntern.newsApp.utils.ApiException
import retrofit2.Response

abstract class BaseRepository {
    suspend fun <T:Any>apiRequest(api: suspend () -> Response<T>): T {
        val response = api.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw ApiException(response.code().toString())
        }
    }

    /*suspend fun <T> apiRequest(apiRequest: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiRequest.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Log.e("HttpException", throwable.toString())
                        Resource.Failure(
                            false,
                            throwable.code(),
                            throwable.response()!!.errorBody()
                        )
                    }
                    else -> {
                        Resource.Failure(true, null, null)
                    }
                }
            }
        }

    }*/


}