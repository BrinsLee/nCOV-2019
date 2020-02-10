package com.brins.ncov_2019.ui.areas

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.brins.ncov_2019.MyApplication
import com.brins.ncov_2019.api.repository.AreaRepository
import com.brins.ncov_2019.api.repository.KnowledgeRepository
import com.brins.ncov_2019.model.KnowledgeResult
import com.brins.ncov_2019.model.ProvincesResult
import com.brins.ncov_2019.ui.base.BaseViewModel

class AreaViewModel (private val repository: AreaRepository) : BaseViewModel() {
    var metaArea = MutableLiveData<List<ProvincesResult>>()

    fun fetchArea() {
        launch({
            val data = repository.getArea()
            metaArea.value = data
        },{
            Toast.makeText(MyApplication.context, it.message, Toast.LENGTH_SHORT).show()
        })
    }
}