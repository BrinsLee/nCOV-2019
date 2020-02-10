package com.brins.ncov_2019.ui.rurmor


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.brins.ncov_2019.R
import com.brins.ncov_2019.databinding.FragmentRumorBinding
import com.brins.ncov_2019.model.NationalDataResult
import com.brins.ncov_2019.model.RumorResult
import com.brins.ncov_2019.ui.adapter.CommonViewAdapter
import com.brins.ncov_2019.ui.adapter.ViewHolder
import com.brins.ncov_2019.ui.base.BaseDBFragment
import com.brins.ncov_2019.ui.statistics.StatisticsViewModel
import com.brins.ncov_2019.utils.InjectorUtil
import com.brins.ncov_2019.utils.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragment_rumor.*

class RumorDBFragment : BaseDBFragment<FragmentRumorBinding>() {


    private var mRumors: ArrayList<RumorResult> = arrayListOf()
    private lateinit var mAdapter: CommonViewAdapter<RumorResult>

    override fun getLayoutId(): Int {
        return R.layout.fragment_rumor
    }


    private val statisticsViewModel by lazy {
        ViewModelProvider(
            this,
            InjectorUtil.getStatisticsModelFactory()
        ).get(StatisticsViewModel::class.java)
    }

    private val rumorsViewModel by lazy {
        ViewModelProvider(
            this,
            InjectorUtil.getRumorsModelFactory()
        ).get(RumorViewModel::class.java)
    }

    override fun initEventAndData() {
        statisticsViewModel.metaNationalData.observe(this,
            Observer<NationalDataResult> {
                mBinding.viewModel = statisticsViewModel
            })
        rumorsViewModel.metaRumors.observe(this, Observer<List<RumorResult>> {
            mRumors.addAll(it)
            mAdapter = object :
                CommonViewAdapter<RumorResult>(mContext, R.layout.adapter_rumor_item, mRumors) {
                override fun converted(holder: ViewHolder, t: RumorResult, position: Int) {

                    holder.setText(R.id.title, t.title)
                    holder.setText(R.id.summary, t.mainSummary)
                    holder.setText(R.id.main_summary, t.body)
                }
            }
            rumors_recycler.layoutManager = LinearLayoutManager(mContext)
            rumors_recycler.adapter = mAdapter
            rumors_recycler.addItemDecoration(
                SpacesItemDecoration(
                    mContext,
                    5,
                    R.color.white1_80
                )
            )
        })
        statisticsViewModel.fetchNationalData()
        rumorsViewModel.fetchRumors()
    }

    override fun isDataBinding(): Boolean {
        return true
    }
}
