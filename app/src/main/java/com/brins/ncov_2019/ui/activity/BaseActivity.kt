package com.brins.ncov_2019.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import butterknife.ButterKnife
import java.lang.IllegalArgumentException

abstract class BaseActivity : AppCompatActivity() {

    protected open var TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isDataBinding()){
            val id = getLayoutId()
            if (id == 0){
                throw IllegalArgumentException("must provide LayoutId")
            }else{
                setContentView(id)
            }
        }
        ButterKnife.bind(this)
        initEventAndData()
    }

    protected open fun initEventAndData(){

    }

    protected open fun isStatusBarTranslucent(): Boolean {
        return true
    }

    protected open fun isDataBinding() : Boolean {
        return false
    }

    protected open fun getLayoutId() : Int{
        return 0
    }
}
