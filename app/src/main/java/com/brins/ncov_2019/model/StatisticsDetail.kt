package com.brins.ncov_2019.model

import androidx.annotation.ColorRes
import retrofit2.http.GET

class StatisticsDetail(
    var count: Int, var increase: Int
    ,var type: String
) {
    val increaseStr: String
        get() {
            if (increase >= 0)
                return "+$increase"
            else
                return "$increase"
        }
    val countStr : String
        get() {
            return count.toString()
        }
}