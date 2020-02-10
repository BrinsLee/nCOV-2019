package com.brins.ncov_2019.ui.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brins.ncov_2019.api.repository.StatisticsRepository

class StatisticsModelFactory (private val repository: StatisticsRepository): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StatisticsViewModel(repository) as T
    }
}