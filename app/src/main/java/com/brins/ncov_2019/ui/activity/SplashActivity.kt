package com.brins.ncov_2019.ui.activity

import android.content.Intent
import android.os.Message
import com.brins.ncov_2019.utils.WeakHandler
import com.brins.ncov_2019.utils.setTranslucent
import com.brins.ncov_2019.R


class SplashActivity : BaseActivity(), WeakHandler.IHandler {

    private var mForceGoMain: Boolean = false
    private val AD_TIME_OUT : Long = 2000
    private val MSG_GO_MAIN = 1
    private var mHasLoaded: Boolean = false
    private val mHandler = WeakHandler(this)

    override fun handleMsg(msg: Message) {
        if (msg.what == MSG_GO_MAIN) {
            if (!mHasLoaded) {
                jump2MainSplash()
            }
        }
    }

    override fun initEventAndData() {
        super.initEventAndData()
        setTranslucent(this)
        mHandler.sendEmptyMessageDelayed(MSG_GO_MAIN, AD_TIME_OUT)
    }

    override fun onResume() {
        if (mForceGoMain) {
            mHandler.removeCallbacksAndMessages(null)
            jump2MainSplash()
        }
        super.onResume()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    private fun jump2MainSplash() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStop() {
        super.onStop()
        mForceGoMain = true
    }
}
