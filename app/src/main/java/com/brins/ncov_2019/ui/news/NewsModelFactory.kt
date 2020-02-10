package com.brins.ncov_2019.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brins.ncov_2019.api.repository.NewsRepository

class NewsModelFactory(private val repository: NewsRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(repository) as T
    }
}