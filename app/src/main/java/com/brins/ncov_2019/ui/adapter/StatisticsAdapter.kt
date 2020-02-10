package com.brins.ncov_2019.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brins.ncov_2019.R
import com.brins.ncov_2019.model.ProvincesResult
import kotlin.collections.ArrayList
import android.util.SparseArray


class StatisticsAdapter(var mContext: Context, mList: ArrayList<ProvincesResult>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mTitlePos = 0
    private val firstBeanSparseArray = SparseArray<ProvincesResult>()//存储省份数据
    private val secondBeanSparseArray = SparseArray<ProvincesResult.city>()//存储城市数据

    init {
        for (i in 0 until mList.size) {
            firstBeanSparseArray.put(i, mList[i])
        }
    }

    companion object {
        val TYPE_PROVINCE = 100
        val TYPE_CITY = 101
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_PROVINCE -> {
                FirstViewHolder(
                    LayoutInflater.from(mContext).inflate(
                        R.layout.adapter_area_item,
                        parent,
                        false
                    )
                )
            }
            else -> {
                SecondViewHolder(
                    LayoutInflater.from(mContext).inflate(
                        R.layout.adapter_area_subitem,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return firstBeanSparseArray.size() + secondBeanSparseArray.size()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == TYPE_PROVINCE) {
            (holder as FirstViewHolder).areaTv.text = firstBeanSparseArray[position].provinceName
            holder.confrimTv.text = firstBeanSparseArray[position].confirmedCount.toString()
            holder.deadTv.text = firstBeanSparseArray[position].deadCount.toString()
            holder.cureTv.text = firstBeanSparseArray[position].curedCount.toString()
            holder.expendIv.setImageResource(if (firstBeanSparseArray[position].isExpend) R.drawable.ic_expand_more else R.drawable.ic_expand_less)


        } else {
            (holder as SecondViewHolder).areaTv.text = secondBeanSparseArray[position].cityName
            holder.confrimTv.text = secondBeanSparseArray[position].confirmedCount.toString()
            holder.deadTv.text = secondBeanSparseArray[position].deadCount.toString()
            holder.cureTv.text = secondBeanSparseArray[position].curedCount.toString()

        }
    }

    override fun getItemViewType(position: Int): Int {
        if (secondBeanSparseArray.get(position) != null) {
            return TYPE_CITY
        }
        return TYPE_PROVINCE
    }

    private inner class FirstViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var areaTv: TextView = itemView.findViewById(R.id.area_tv)
        internal var confrimTv: TextView = itemView.findViewById(R.id.confirm_tv)
        internal var deadTv: TextView = itemView.findViewById(R.id.dead_tv)
        internal var cureTv: TextView = itemView.findViewById(R.id.cure_tv)
        internal var expendIv: ImageView = itemView.findViewById(R.id.expend_iv)


        init {

            //item点击事件监听
            expendIv.setOnClickListener {
                if (firstBeanSparseArray.get(layoutPosition).isExpend) {
                    //设置第二级布局是否展开的flag
                    firstBeanSparseArray.get(layoutPosition).isExpend = false
                    //获取要移除的第二级布局个数
                    val addedSubNum = firstBeanSparseArray.get(layoutPosition).cities!!.size
                    //移除第二级布局
                    expendIv.setImageResource(R.drawable.ic_expand_less)
                    removeItems(layoutPosition, addedSubNum)
                    notifyItemRangeRemoved(layoutPosition + 1, addedSubNum)
                } else {
                    //设置第二级布局是否展开的flag
                    firstBeanSparseArray.get(layoutPosition).isExpend = true
                    expendIv.setImageResource(R.drawable.ic_expand_more)
                    //加载数据并获取载入的第二级布局个数
                    val list: ArrayList<ProvincesResult.city> = ArrayList()
                    for (i in 0 until firstBeanSparseArray.get(layoutPosition).cities!!.size) {
                        list.add(firstBeanSparseArray.get(layoutPosition).cities!![i])
                    }
                    val addedSubNum = setEachFlows(layoutPosition, list)
                    //添加第二级布局
                    notifyItemRangeInserted(layoutPosition + 1, addedSubNum)
                }
            }
        }
    }

    private inner class SecondViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var areaTv: TextView = itemView.findViewById(R.id.area_tv)
        var confrimTv: TextView = itemView.findViewById(R.id.confirm_tv)
        var deadTv: TextView = itemView.findViewById(R.id.dead_tv)
        var cureTv: TextView = itemView.findViewById(R.id.cure_tv)
    }

    fun setEachFlows(parentPosition: Int, list: List<ProvincesResult.city>): Int {

        //更新position大于当前点击的position的第一级布局的item的position
        for (i in itemCount - 1 downTo parentPosition + 1) {
            val index = firstBeanSparseArray.indexOfKey(i)
            if (index < 0) {
                continue
            }
            val dailyFlow = firstBeanSparseArray.valueAt(index)
            firstBeanSparseArray.removeAt(index)
            firstBeanSparseArray.put(list.size + i, dailyFlow)
        }
        //更新position大于当前点击的position的第二级布局的item的position
        for (i in itemCount - 1 downTo parentPosition + 1) {
            val index = secondBeanSparseArray.indexOfKey(i)
            if (index < 0) {
                continue
            }
            val eachFlow = secondBeanSparseArray.valueAt(index)
            secondBeanSparseArray.removeAt(index)
            secondBeanSparseArray.append(list.size + i, eachFlow)
        }
        //把获取到的数据根据相应的position放入SparseArray中。
        for (i in list.indices) {
            secondBeanSparseArray.put(parentPosition + i + 1, list[i])
        }
        return list.size
    }

    private fun removeItems(clickPosition: Int, addedSubNum: Int) {
        //更新position大于当前点击的position的第一级布局item的position
        val temp: SparseArray<ProvincesResult> = SparseArray()

        for (i in itemCount - 1 downTo clickPosition + addedSubNum + 1) {
            val index = firstBeanSparseArray.indexOfKey(i)
            if (index < 0) {
                continue
            }
            val dailyFlow = firstBeanSparseArray.valueAt(index)
            firstBeanSparseArray.removeAt(index)
            temp.put(i - addedSubNum, dailyFlow)
        }
        for (i in 0 until temp.size()) {

            val key = temp.keyAt(i)
            firstBeanSparseArray.put(key, temp.get(key))
        }
        //更新position大于当前点击的position的第二级布局item的position
        val temp2: SparseArray<ProvincesResult.city> = SparseArray()
        for (i in itemCount - 1 downTo clickPosition + addedSubNum + 1) {
            val index = secondBeanSparseArray.indexOfKey(i)
            if (index < 0) {
                continue
            }
            val eachFlow = secondBeanSparseArray.valueAt(index)
            secondBeanSparseArray.removeAt(index)
            temp2.put(i - addedSubNum, eachFlow)
        }
        for (i in 1..addedSubNum) {
            //移除被折叠的第二级布局数据
            secondBeanSparseArray.remove(clickPosition + i)
        }
        for (i in 0 until temp2.size()) {

            val key = temp2.keyAt(i)
            secondBeanSparseArray.put(key, temp2.get(key))
        }

    }
}