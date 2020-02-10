package com.brins.ncov_2019.api
import com.brins.ncov_2019.config.WIKI_LIST
import com.brins.ncov_2019.model.KnowledgeResult
import retrofit2.Call
import retrofit2.http.GET

interface KnowledgeService {

    @GET(WIKI_LIST)
    fun fetchWiki() : Call<KnowledgeResult>
}