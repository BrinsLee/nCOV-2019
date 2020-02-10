package com.brins.ncov_2019.api.repository

import com.brins.ncov_2019.api.network.StatisticsNetwork
import com.brins.ncov_2019.model.NationalDataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StatisticsRepository private constructor(
    val network: StatisticsNetwork
) {
    companion object{
        private lateinit var instance: StatisticsRepository

        fun getInstance(network: StatisticsNetwork): StatisticsRepository {
            if (!::instance.isInitialized) {
                synchronized(StatisticsRepository::class.java) {
                    if (!::instance.isInitialized) {
                        this.instance = StatisticsRepository(network)
                    }
                }
            }
            return this.instance
        }
    }

    suspend fun getStatistics() : NationalDataResult = requestNationalData()

    private suspend fun requestNationalData(): NationalDataResult = withContext(Dispatchers.IO){
        val news = network.fetchStatistics()
        news

    }
}