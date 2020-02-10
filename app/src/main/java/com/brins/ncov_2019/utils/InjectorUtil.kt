package com.brins.ncov_2019.utils

import com.brins.ncov_2019.api.network.NewsNetwork
import com.brins.ncov_2019.api.network.RumorNetwork
import com.brins.ncov_2019.api.network.StatisticsNetwork
import com.brins.ncov_2019.api.repository.NewsRepository
import com.brins.ncov_2019.api.repository.RumorRepository
import com.brins.ncov_2019.api.repository.StatisticsRepository
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

/*    fun getPlaceModelFactory() = PlaceModelFactory(getPlaceRepository())

    fun getWeatherModelFactory() = WeatherModelFactory(getWeatherRepository())

    fun getMainModelFactory() = MainModelFactory(getWeatherRepository())*/

}