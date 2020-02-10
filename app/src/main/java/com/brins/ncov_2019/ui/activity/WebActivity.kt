package com.brins.ncov_2019.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.OnClick
import com.brins.ncov_2019.R
import com.brins.ncov_2019.utils.setTranslucent
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity() {

    companion object {
        fun startThis(activity: BaseActivity,url: String){
            if (url.isNullOrEmpty()){
                return
            }
            val intent = Intent(activity,WebActivity::class.java)
            intent.putExtra(LOAD_URL,url)
            activity.startActivity(intent)
        }
        val LOAD_URL = "LOAD_URL"
    }

    private var loadURL : String = ""

    override fun getLayoutId(): Int {
        return R.layout.activity_web
    }

    override fun initEventAndData() {
        super.initEventAndData()
        loadURL = intent.getStringExtra(LOAD_URL)!!
        setTranslucent(this)
        val webSettings = webview_view.settings
        webSettings.javaScriptEnabled = true
        //设置可以访问文件
        webSettings.allowFileAccess = true
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE//设置不缓存
        webSettings.setAppCacheEnabled(false)//设置不缓存
        //设置支持缩放
        webSettings.builtInZoomControls = true
        webview_view.loadUrl(loadURL)
        webview_view.webViewClient = webViewClient()
        webview_view.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (webview_progress != null) {
                    webview_progress.progress = newProgress
                }

                if (newProgress == 100) {
                    if (webview_progress != null) {
                        webview_progress.visibility = View.GONE
                    }

                }
            }
        }

    }

    private inner class webViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview_view.canGoBack()) {
            webview_view.goBack() //goBack()表示返回WebView的上一页面
            return true
        }
        finish()//结束退出程序
        return false
    }

    @OnClick(R.id.return_img)
    fun onClick(){
        finish()
    }
}
