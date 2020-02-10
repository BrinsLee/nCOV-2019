package com.brins.ncov_2019.api.network

import com.brins.ncov_2019.api.ApiHelper
import retrofit2.await

class AreaNetwork private constructor(){

    companion object{
        private var instance: AreaNetwork? = null

        fun getInstance(): AreaNetwork {
            if (instance == null) {
                synchronized(AreaNetwork::class.java) {
                    if (instance == null) {
                        instance = AreaNetwork()
                    }
                }
            }
            return instance!!
        }
    }

    private val areaService = ApiHelper.getAreaService()

    suspend fun fetchArea() = areaService.fetchArea().await()
}