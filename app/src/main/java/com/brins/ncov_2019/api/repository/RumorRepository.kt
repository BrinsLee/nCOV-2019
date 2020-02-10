package com.brins.ncov_2019.api.repository

import com.brins.ncov_2019.api.network.RumorNetwork
import com.brins.ncov_2019.model.RumorResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RumorRepository private constructor(
    val network: RumorNetwork
) {

    companion object{
        private lateinit var instance: RumorRepository

        fun getInstance(network: RumorNetwork): RumorRepository {
            if (!::instance.isInitialized) {
                synchronized(RumorRepository::class.java) {
                    if (!::instance.isInitialized) {
                        this.instance = RumorRepository(network)
                    }
                }
            }
            return this.instance
        }
    }

    suspend fun getRumor() : List<RumorResult> = requestRumorData()

    private suspend fun requestRumorData(): List<RumorResult> = withContext(Dispatchers.IO){
        val rumors = network.fetchRumor()
        rumors

    }
}