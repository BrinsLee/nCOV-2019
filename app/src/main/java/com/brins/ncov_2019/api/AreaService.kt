package com.brins.ncov_2019.api

import com.brins.ncov_2019.config.AREA_STATE
import com.brins.ncov_2019.model.ProvincesResult
import retrofit2.Call
import retrofit2.http.GET

interface AreaService {

    @GET(AREA_STATE)
    fun fetchArea() : Call<List<ProvincesResult>>
}