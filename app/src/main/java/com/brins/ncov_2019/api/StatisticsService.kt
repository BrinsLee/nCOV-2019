package com.brins.ncov_2019.api

import com.brins.ncov_2019.config.STATISTICS_SERVICE
import com.brins.ncov_2019.config.TIME_LINE_SERVICE
import com.brins.ncov_2019.model.NationalDataResult
import retrofit2.Call
import retrofit2.http.GET

interface StatisticsService {

    @GET(STATISTICS_SERVICE)
    fun fetchStatistics() : Call<NationalDataResult>
}