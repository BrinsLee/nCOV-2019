package com.brins.ncov_2019.api

import com.brins.ncov_2019.config.BASE_URL


object ApiHelper {

    //    private const val BASE_URL = "http://guolin.tech/"
    private var mNewsService: NewsService? = null
    private var mStatisticsService: StatisticsService? = null
    private var mRumorService: RumorService? = null
    private var mWikiService: KnowledgeService? = null
    private var mAreaService: AreaService? = null




    fun getNewsService(): NewsService {
        if (mNewsService == null) {
            synchronized(NewsService::class.java) {
                if (mNewsService == null) {
                    mNewsService =
                        RetrofitFactory.newRetrofit(BASE_URL).create(NewsService::class.java)
                }
            }
        }
        return mNewsService!!
    }

    fun getStatisticsService(): StatisticsService {
        if (mStatisticsService == null) {
            synchronized(StatisticsService::class.java) {
                if (mStatisticsService == null) {
                    mStatisticsService =
                        RetrofitFactory.newRetrofit(BASE_URL).create(StatisticsService::class.java)
                }
            }
        }
        return mStatisticsService!!
    }

    fun getRumorService(): RumorService {
        if (mRumorService == null) {
            synchronized(RumorService::class.java) {
                if (mRumorService == null) {
                    mRumorService =
                        RetrofitFactory.newRetrofit(BASE_URL).create(RumorService::class.java)
                }
            }
        }
        return mRumorService!!
    }

    fun getWikiService(): KnowledgeService {
        if (mWikiService == null) {
            synchronized(KnowledgeService::class.java) {
                if (mWikiService == null) {
                    mWikiService =
                        RetrofitFactory.newRetrofit(BASE_URL).create(KnowledgeService::class.java)
                }
            }
        }
        return mWikiService!!
    }

    fun getAreaService(): AreaService {
        if (mAreaService == null) {
            synchronized(AreaService::class.java) {
                if (mAreaService == null) {
                    mAreaService =
                        RetrofitFactory.newRetrofit(BASE_URL).create(AreaService::class.java)
                }
            }
        }
        return mAreaService!!
    }
}