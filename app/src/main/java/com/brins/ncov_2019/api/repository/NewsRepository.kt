package com.brins.ncov_2019.api.repository

import com.brins.ncov_2019.api.network.NewsNetwork
import com.brins.ncov_2019.model.TimeNewResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository private constructor(
    val network: NewsNetwork) {

    companion object{
        private lateinit var instance: NewsRepository

        fun getInstance(network: NewsNetwork): NewsRepository {
            if (!::instance.isInitialized) {
                synchronized(NewsRepository::class.java) {
                    if (!::instance.isInitialized) {
                        this.instance = NewsRepository(network)
                    }
                }
            }
            return this.instance
        }
    }

    suspend fun getNews() : List<TimeNewResult> = requestNews()

    private suspend fun requestNews(): List<TimeNewResult> = withContext(Dispatchers.IO){
        val news = network.fetchNews()
        news

    }
}