package com.brins.ncov_2019.api.network

import com.brins.ncov_2019.api.ApiHelper
import retrofit2.await

class StatisticsNetwork private constructor() {

    companion object {
        private var instance: StatisticsNetwork? = null

        fun getInstance(): StatisticsNetwork {
            if (instance == null) {
                synchronized(StatisticsNetwork::class.java) {
                    if (instance == null) {
                        instance = StatisticsNetwork()
                    }
                }
            }
            return instance!!
        }
    }

    private val statisticsService = ApiHelper.getStatisticsService()

    suspend fun fetchStatistics() = statisticsService.fetchStatistics().await()
}