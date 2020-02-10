package com.brins.ncov_2019.ui.knowledge

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.brins.ncov_2019.MyApplication
import com.brins.ncov_2019.api.repository.KnowledgeRepository
import com.brins.ncov_2019.model.KnowledgeResult
import com.brins.ncov_2019.ui.base.BaseViewModel

class KnowledgeViewModel(private val repository: KnowledgeRepository) : BaseViewModel() {
    var metaWiki = MutableLiveData<KnowledgeResult>()

    fun fetchRumors() {
        launch({
            val data = repository.getWiki()
            metaWiki.value = data
        },{
            Toast.makeText(MyApplication.context, it.message, Toast.LENGTH_SHORT).show()
        })
    }
}