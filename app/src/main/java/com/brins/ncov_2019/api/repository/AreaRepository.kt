package com.brins.ncov_2019.api.repository

import com.brins.ncov_2019.api.network.AreaNetwork
import com.brins.ncov_2019.model.ProvincesResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AreaRepository private constructor(
    val network: AreaNetwork
) {

    companion object{
        private lateinit var instance: AreaRepository

        fun getInstance(network: AreaNetwork): AreaRepository {
            if (!::instance.isInitialized) {
                synchronized(AreaRepository::class.java) {
                    if (!::instance.isInitialized) {
                        this.instance = AreaRepository(network)
                    }
                }
            }
            return this.instance
        }
    }

    suspend fun getArea() : List<ProvincesResult> = requestArea()

    private suspend fun requestArea(): List<ProvincesResult> = withContext(Dispatchers.IO){
        val wiki = network.fetchArea()
        wiki

    }
}