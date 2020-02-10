package com.brins.ncov_2019.ui.adapter

import android.content.Context

abstract class CommonViewAdapter<T>(context: Context, var layoutId: Int, var list: ArrayList<T>) :
    BaseRecyclerAdapter<T>(context, list) {

    companion object {
        val TAG: String = "TreeRecyclerViewAdapter"
    }

    init {
        addItemViewDelegate(object : ItemViewDelegate<T> {
            override fun convert(holder: ViewHolder, t: T, position: Int) {
                converted(holder, t, position)
            }

            override fun getItemViewLayoutId(): Int {
                return layoutId
            }

            override fun isForViewType(item: T, position: Int): Boolean {
                return true
            }

        })
    }

    protected abstract fun converted(holder: ViewHolder, t: T, position: Int)



}