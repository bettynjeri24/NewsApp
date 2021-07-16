package com.eclecticsIntern.newsApp.data.db

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataPreferences(context: Context) {
    private val appContext = context.applicationContext
    private val dataStore: DataStore<Preferences> =
        appContext.createDataStore(name = "articleFetchTome")

    companion object {
        private val TIME_LAST_FETCHED = preferencesKey<String>("last_fetched")
    }

    suspend fun saveTimeArticleFetched(time: String) {
        dataStore.edit {
            it[TIME_LAST_FETCHED] = time
        }
    }

    val getTimeArticleWasSaved: Flow<String?>
        get() = dataStore.data.catch { err ->
            if (err is IOException) emit(emptyPreferences()) else throw err
        }.map {
            it[TIME_LAST_FETCHED]
        }

}