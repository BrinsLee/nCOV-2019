package com.brins.ncov_2019.model

import com.google.gson.annotations.SerializedName

class NationalDataResult {
    var id = 0
    var createTime : Long = 0
    var modifyTime : Long = 0
    @SerializedName("imgUrl")
    var nationalImgUrl : String = ""
    @SerializedName("dailyPics")
    var dailyTrend = arrayOf<String>()

    //确诊病例
    var confirmedCount = 0
    //疑似病例
    var suspectedCount = 0
    //治愈病例
    var curedCount = 0
    //死亡病例
    var deadCount = 0
    //严重
    var seriousCount = 0
    //怀疑增加人数
    var suspectedIncr = 0
    //确诊增加人数
    var confirmedIncr = 0
    //治愈增加
    var curedIncr = 0
    //死亡增加
    var deadIncr = 0
    //严重增加
    var seriousIncr = 0
}