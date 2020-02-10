package com.brins.ncov_2019.ui.knowledge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brins.ncov_2019.api.repository.KnowledgeRepository


class KnowledgeModelFactory (private val repository: KnowledgeRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KnowledgeViewModel(repository) as T
    }
}