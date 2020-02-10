package com.brins.ncov_2019.api.network

import com.brins.ncov_2019.api.ApiHelper
import retrofit2.await

class RumorNetwork private constructor(){

    companion object{
        private var instance: RumorNetwork? = null

        fun getInstance(): RumorNetwork {
            if (instance == null) {
                synchronized(RumorNetwork::class.java) {
                    if (instance == null) {
                        instance = RumorNetwork()
                    }
                }
            }
            return instance!!
        }
    }

    private val rumorService = ApiHelper.getRumorService()

    suspend fun fetchRumor() = rumorService.fetchRumorList().await()
}