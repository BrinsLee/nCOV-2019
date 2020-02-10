package com.brins.ncov_2019.ui.adapter

interface ItemViewDelegate <T>{
    fun getItemViewLayoutId(): Int

    fun isForViewType(item: T, position: Int): Boolean

    fun convert(holder: ViewHolder, t: T, position: Int)

}