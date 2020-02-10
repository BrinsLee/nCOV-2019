package com.brins.ncov_2019.ui.rurmor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brins.ncov_2019.api.repository.NewsRepository
import com.brins.ncov_2019.api.repository.RumorRepository
import com.brins.ncov_2019.ui.news.NewsViewModel

class RumorModelFactory(private val repository: RumorRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RumorViewModel(repository) as T
    }
}