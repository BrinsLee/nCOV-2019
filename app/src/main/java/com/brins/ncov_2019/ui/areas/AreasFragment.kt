package com.brins.ncov_2019.ui.areas


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.brins.ncov_2019.R
import com.brins.ncov_2019.model.KnowledgeResult
import com.brins.ncov_2019.model.ProvincesResult
import com.brins.ncov_2019.ui.adapter.StatisticsAdapter
import com.brins.ncov_2019.ui.base.BaseFragment
import com.brins.ncov_2019.ui.knowledge.KnowledgeViewModel
import com.brins.ncov_2019.utils.InjectorUtil
import com.brins.ncov_2019.utils.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragment_areas.*


class AreasFragment : BaseFragment() {


    private lateinit var mAdapter: StatisticsAdapter
    private var mList = arrayListOf<ProvincesResult>()
    private val areaViewModel by lazy {
        ViewModelProvider(
            this,
            InjectorUtil.getAreaModelFactory()
        ).get(AreaViewModel::class.java)
    }

    override fun initEventAndData() {
        areaViewModel.metaArea.observe(this, Observer<List<ProvincesResult>> {
            mList.addAll(it)
            mAdapter = StatisticsAdapter(mContext, mList)
            recycler_area.adapter = mAdapter
            recycler_area.layoutManager = LinearLayoutManager(mContext)
            recycler_area.addItemDecoration(
                SpacesItemDecoration(
                    mContext,
                    5,
                    R.color.white1_80
                )
            )
        })
        areaViewModel.fetchArea()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_areas
    }


}
