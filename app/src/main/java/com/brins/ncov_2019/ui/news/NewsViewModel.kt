package com.brins.ncov_2019.ui.news

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.brins.ncov_2019.MyApplication
import com.brins.ncov_2019.api.repository.NewsRepository
import com.brins.ncov_2019.model.TimeNewResult
import com.brins.ncov_2019.ui.base.BaseViewModel

class NewsViewModel(private val repository: NewsRepository) : BaseViewModel() {
    var metaNews = MutableLiveData<List<TimeNewResult>>()

    fun fetchNews() {
        launch({
            val data = repository.getNews()
            metaNews.value = data
        },{
            Toast.makeText(MyApplication.context, it.message, Toast.LENGTH_SHORT).show()
        })
    }



}