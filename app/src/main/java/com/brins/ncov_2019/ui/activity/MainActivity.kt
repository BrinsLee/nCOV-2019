package com.brins.ncov_2019.ui.activity

import android.view.View
import androidx.fragment.app.Fragment
import butterknife.OnClick
import com.brins.ncov_2019.adapter.MainPagerAdapter
import com.brins.ncov_2019.ui.news.NewsFragment
import com.brins.ncov_2019.utils.getStatusBarHeight
import com.brins.ncov_2019.utils.setTranslucent
import com.brins.ncov_2019.R
import com.brins.ncov_2019.ui.areas.AreasFragment
import com.brins.ncov_2019.ui.knowledge.KnowledgeFragment
import com.brins.ncov_2019.ui.rurmor.RumorDBFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_bar.*
import kotlinx.android.synthetic.main.view_common_toolbar.*


class MainActivity : BaseActivity() {

    private var currentPage = 0
    private var list = mutableListOf<Fragment>()
    private val adapter by lazy { MainPagerAdapter(supportFragmentManager, list) }
    private var mClickTime: Long = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initEventAndData() {
        super.initEventAndData()
        setTranslucent(this)
        toolbar.setPadding(0, getStatusBarHeight(this), 0, 0)
        list.add(NewsFragment())
        list.add(RumorDBFragment())
        list.add(AreasFragment())
        list.add(KnowledgeFragment())
        initViewPagerAndTabLay()
    }

    private fun initViewPagerAndTabLay() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        tv_title.text = "首页"
        view_pager.adapter = adapter
        view_pager.offscreenPageLimit = 3
        changeTab(0)
    }

    @OnClick(
        R.id.tab_main_btn,
        R.id.tab_main_tv,
        R.id.tab_rumor_btn,
        R.id.tab_rumor_tv,
        R.id.tab_statistics_btn,
        R.id.tab_statistics_tv,
        R.id.tab_knowledge_btn,
        R.id.tab_knowledge_tv
    )
    fun onClick(view: View) {
        when (view.id) {
            R.id.tab_main_btn,
            R.id.tab_main_tv -> {
                changeTab(0)
            }
            R.id.tab_rumor_btn,
            R.id.tab_rumor_tv -> {
                changeTab(1)
            }
            R.id.tab_statistics_btn,
            R.id.tab_statistics_tv -> {
                changeTab(2)
            }
            R.id.tab_knowledge_btn,
            R.id.tab_knowledge_tv -> {
                changeTab(3)
            }
        }
    }

    private fun changeTab(position: Int) {
        tab_main_btn.isSelected = false
        tab_main_tv.isSelected = false
        tab_rumor_btn.isSelected = false
        tab_rumor_tv.isSelected = false
        tab_statistics_btn.isSelected = false
        tab_statistics_tv.isSelected = false
        tab_knowledge_btn.isSelected = false
        tab_knowledge_tv.isSelected = false
        currentPage = position

        when (position) {
            0 -> {
                tab_main_btn.isSelected = true
                tab_main_tv.isSelected = true
                tv_title.text = getString(R.string.main_tab)
            }
            1 -> {
                tab_rumor_tv.isSelected = true
                tab_rumor_btn.isSelected = true
                tv_title.text = getString(R.string.rumor)
            }
            2 -> {
                tab_statistics_btn.isSelected = true
                tab_statistics_tv.isSelected = true
                tv_title.text = getString(R.string.statistics)

            }
            3 -> {
                tab_knowledge_btn.isSelected = true
                tab_knowledge_tv.isSelected = true
                tv_title.text = getString(R.string.knowledge)
            }
        }
        view_pager.currentItem = position

    }
}
