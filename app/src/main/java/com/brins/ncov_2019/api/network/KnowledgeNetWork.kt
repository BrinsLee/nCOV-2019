package com.brins.ncov_2019.api.network

import com.brins.ncov_2019.api.ApiHelper
import retrofit2.await

class KnowledgeNetWork private constructor(){

    companion object{
        private var instance: KnowledgeNetWork? = null

        fun getInstance(): KnowledgeNetWork {
            if (instance == null) {
                synchronized(KnowledgeNetWork::class.java) {
                    if (instance == null) {
                        instance = KnowledgeNetWork()
                    }
                }
            }
            return instance!!
        }
    }

    private val wikiService = ApiHelper.getWikiService()

    suspend fun fetchWiki() = wikiService.fetchWiki().await()
}