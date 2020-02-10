package com.brins.ncov_2019.utils

import com.brins.ncov_2019.api.network.*
import com.brins.ncov_2019.api.repository.*
import com.brins.ncov_2019.ui.areas.AreaModelFactory
import com.brins.ncov_2019.ui.areas.AreaViewModel
import com.brins.ncov_2019.ui.knowledge.KnowledgeModelFactory
import com.brins.ncov_2019.ui.news.NewsModelFactory
import com.brins.ncov_2019.ui.rurmor.RumorModelFactory
import com.brins.ncov_2019.ui.statistics.StatisticsModelFactory


object InjectorUtil {

    private fun getNewsRepository() = NewsRepository.getInstance(NewsNetwork.getInstance())

    fun getNewsModelFactory() = NewsModelFactory(getNewsRepository())

    private fun getStatisticsRepository() = StatisticsRepository.getInstance(StatisticsNetwork.getInstance())

    fun getStatisticsModelFactory() = StatisticsModelFactory(getStatisticsRepository())

    private fun getRumorsRepository() = RumorRepository.getInstance(RumorNetwork.getInstance())

    fun getRumorsModelFactory() = RumorModelFactory(getRumorsRepository())

    private fun getKnowledgeRepository() = KnowledgeRepository.getInstance(KnowledgeNetWork.getInstance())

    fun getKnowledgeModelFactory() = KnowledgeModelFactory(getKnowledgeRepository())

    private fun getAreaRepository() = AreaRepository.getInstance(AreaNetwork.getInstance())

    fun getAreaModelFactory() = AreaModelFactory(getAreaRepository())

/*    fun getPlaceModelFactory() = PlaceModelFactory(getPlaceRepository())

    fun getWeatherModelFactory() = WeatherModelFactory(getWeatherRepository())

    fun getMainModelFactory() = MainModelFactory(getWeatherRepository())*/

}