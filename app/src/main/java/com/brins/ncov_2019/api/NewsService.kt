package com.brins.ncov_2019.api

import com.brins.ncov_2019.config.TIME_LINE_SERVICE
import com.brins.ncov_2019.model.TimeNewResult
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {

    @GET(TIME_LINE_SERVICE)
    fun fetchTimeNew() : Call<List<TimeNewResult>>
}