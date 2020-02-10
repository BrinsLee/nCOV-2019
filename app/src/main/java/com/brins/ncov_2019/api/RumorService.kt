package com.brins.ncov_2019.api

import com.brins.ncov_2019.config.RUMOR_LIST
import com.brins.ncov_2019.model.RumorResult
import retrofit2.Call
import retrofit2.http.GET

interface RumorService {

    @GET(RUMOR_LIST)
    fun fetchRumorList(): Call<List<RumorResult>>
}