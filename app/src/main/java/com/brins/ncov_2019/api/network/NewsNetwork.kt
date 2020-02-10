package com.brins.ncov_2019.api.network

import com.brins.ncov_2019.api.ApiHelper
import retrofit2.await

class NewsNetwork private constructor(){

    companion object{
        private var instance: NewsNetwork? = null

        fun getInstance(): NewsNetwork {
            if (instance == null) {
                synchronized(NewsNetwork::class.java) {
                    if (instance == null) {
                        instance = NewsNetwork()
                    }
                }
            }
            return instance!!
        }
    }

    private val newsService = ApiHelper.getNewsService()

    suspend fun fetchNews() = newsService.fetchTimeNew().await()
}