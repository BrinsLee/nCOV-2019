package com.brins.ncov_2019.ui.statistics

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.brins.ncov_2019.MyApplication
import com.brins.ncov_2019.R
import com.brins.ncov_2019.api.repository.StatisticsRepository
import com.brins.ncov_2019.model.NationalDataResult
import com.brins.ncov_2019.model.StatisticsDetail
import com.brins.ncov_2019.ui.base.BaseViewModel

class StatisticsViewModel(private val repository: StatisticsRepository) : BaseViewModel() {

    var metaNationalData = MutableLiveData<NationalDataResult>()
    lateinit var mConfirmed: StatisticsDetail
    lateinit var mSuspected: StatisticsDetail
    lateinit var mCured: StatisticsDetail
    lateinit var mDead: StatisticsDetail


    fun fetchNationalData() {
        launch({
            val data = repository.getStatistics()
            mConfirmed = StatisticsDetail(
                data.confirmedCount, data.confirmedIncr, "确诊"
            )
            mSuspected = StatisticsDetail(
                data.suspectedCount, data.suspectedIncr, "疑似"
            )
            mDead = StatisticsDetail(
                data.deadCount, data.deadIncr, "死亡"
            )
            mCured = StatisticsDetail(
                data.curedCount, data.curedIncr, "治愈"
            )
            metaNationalData.value = data
        }, {
            Toast.makeText(MyApplication.context, it.message, Toast.LENGTH_SHORT).show()
        })
    }
}