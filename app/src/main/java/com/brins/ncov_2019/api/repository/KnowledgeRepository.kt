package com.brins.ncov_2019.api.repository

import com.brins.ncov_2019.api.network.KnowledgeNetWork
import com.brins.ncov_2019.model.KnowledgeResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KnowledgeRepository private constructor(
    val network: KnowledgeNetWork
) {

    companion object{
        private lateinit var instance: KnowledgeRepository

        fun getInstance(network: KnowledgeNetWork): KnowledgeRepository {
            if (!::instance.isInitialized) {
                synchronized(KnowledgeRepository::class.java) {
                    if (!::instance.isInitialized) {
                        this.instance = KnowledgeRepository(network)
                    }
                }
            }
            return this.instance
        }
    }

    suspend fun getWiki() : KnowledgeResult = requestWiki()

    private suspend fun requestWiki(): KnowledgeResult = withContext(Dispatchers.IO){
        val wiki = network.fetchWiki()
        wiki

    }
}