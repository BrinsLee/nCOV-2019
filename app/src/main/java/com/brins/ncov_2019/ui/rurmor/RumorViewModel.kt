package com.brins.ncov_2019.ui.rurmor

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.brins.ncov_2019.MyApplication
import com.brins.ncov_2019.api.repository.RumorRepository
import com.brins.ncov_2019.model.RumorResult
import com.brins.ncov_2019.ui.base.BaseViewModel

class RumorViewModel(private val repository: RumorRepository) : BaseViewModel() {
    var metaRumors = MutableLiveData<List<RumorResult>>()

    fun fetchRumors() {
        launch({
            val data = repository.getRumor()
            metaRumors.value = data
        },{
            Toast.makeText(MyApplication.context, it.message, Toast.LENGTH_SHORT).show()
        })
    }
}