package com.brins.ncov_2019.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.brins.ncov_2019.MyApplication
import com.brins.ncov_2019.R
import com.brins.ncov_2019.config.TimeConstants.MIN
import com.brins.ncov_2019.ui.widget.FontTextView
import com.bumptech.glide.Glide

@BindingAdapter("bind:textColor")
fun FontTextView.textColor(type: String?) {
    when (type) {
        "确诊" -> this.setTextColor(
            ContextCompat.getColor(
                MyApplication.context,
                android.R.color.holo_red_dark
            )
        )
        "疑似" -> this.setTextColor(
            ContextCompat.getColor(
                MyApplication.context,
                R.color.suspected_text
            )
        )
        "死亡" -> this.setTextColor(
            ContextCompat.getColor(
                MyApplication.context,
                android.R.color.black
            )
        )
        "治愈" -> this.setTextColor(
            ContextCompat.getColor(
                MyApplication.context,
                android.R.color.holo_green_dark
            )
        )

    }
}

@BindingAdapter("bind:textDate")
fun TextView.setDateText(date: Long?) {
    date?.let {
        this.text = "截至 ${TimeUtils.getDate(it, 1000, MIN)}"
    }
}

@BindingAdapter("bind:imageUrl")
fun ImageView.loadImageUrl(url : String?){
    url?.let {
        ImageLoadreUtils.getInstance().loadImage(MyApplication.context,ImageLoader.Builder()
            .url(it).imgView(this).bulid())
    }
}