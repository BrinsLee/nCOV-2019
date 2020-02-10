package com.brins.ncov_2019.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainPagerAdapter(fm: FragmentManager, var list: MutableList<Fragment>) : FragmentPagerAdapter(fm) {

    val tabtitle = arrayOf("首页", "辟谣", "统计", "知识")
    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }
}