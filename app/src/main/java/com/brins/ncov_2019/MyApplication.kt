package com.brins.ncov_2019

import android.annotation.SuppressLint
import android.content.Context
import androidx.multidex.MultiDexApplication

class MyApplication : MultiDexApplication() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context : Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}