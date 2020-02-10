package com.brins.ncov_2019.ui.news


import android.view.View
import android.widget.ImageView

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.brins.ncov_2019.model.TimeNewResult
import com.brins.ncov_2019.ui.adapter.CommonViewAdapter
import com.brins.ncov_2019.ui.adapter.ViewHolder
import com.brins.ncov_2019.ui.base.BaseDBFragment
import com.brins.ncov_2019.utils.InjectorUtil
import com.brins.ncov_2019.utils.SpacesItemDecoration
import com.brins.ncov_2019.R
import com.brins.ncov_2019.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : BaseFragment() {

    private lateinit var mAdapter: CommonViewAdapter<TimeNewResult>

    private val newsViewModel by lazy {
        ViewModelProvider(
            this,
            InjectorUtil.getNewsModelFactory()
        ).get(NewsViewModel::class.java)
    }

    override fun initEventAndData() {
        newsViewModel.metaNews.observe(this
            , Observer<List<TimeNewResult>> {
                mAdapter = object : CommonViewAdapter<TimeNewResult>(
                    mContext, R.layout.adapter_news_item,
                    it as ArrayList<TimeNewResult>
                ) {
                    override fun converted(holder: ViewHolder, t: TimeNewResult, position: Int) {
                        if (position == 0) {
                            holder.getView<ImageView>(R.id.news_cover_iv).visibility = View.VISIBLE
                        }
                        holder.setText(R.id.title_tv, t.title)
                        holder.setText(R.id.date_and_source, "${t.pubDateStr}  ${t.infoSource}")
                        holder.setText(R.id.content_tv, t.summary)
                    }

                }
                recycler_news.adapter = mAdapter
                recycler_news.layoutManager = LinearLayoutManager(mContext)
                recycler_news.addItemDecoration(
                    SpacesItemDecoration(
                        mContext,
                        5,
                        R.color.white1_80
                    )
                )
                hideProgress()
            })
        showProgress()
        newsViewModel.fetchNews()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_news
    }
}
