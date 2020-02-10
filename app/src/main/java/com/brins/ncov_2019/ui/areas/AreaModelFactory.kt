package com.brins.ncov_2019.ui.areas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brins.ncov_2019.api.repository.AreaRepository


class AreaModelFactory (private val repository: AreaRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AreaViewModel(repository) as T
    }
}