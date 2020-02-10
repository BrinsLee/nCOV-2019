package com.brins.ncov_2019.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.Typeface
import android.os.Build
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import androidx.collection.SimpleArrayMap
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.brins.ncov_2019.R
import com.brins.ncov_2019.ui.widget.ProgressLoading
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference




class WeakHandler(handler: IHandler) : Handler() {

    interface IHandler {
        fun handleMsg(msg: Message)
    }

    private val mRef: WeakReference<IHandler> = WeakReference(handler)

    override fun handleMessage(msg: Message?) {
        var handler = mRef.get()
        if (handler != null && msg != null) {
            handler.handleMsg(msg)
        }
    }
}


//状态栏相关
fun setTranslucent(activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        // 设置状态栏透明
        val window = activity.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 设置根布局的参数
        /*            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);*/
        val decorView = window.decorView
        val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        decorView.systemUiVisibility = option
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }
}

fun setColorTranslucent(activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        // 设置状态栏透明
        val window = activity.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 设置根布局的参数
        /*            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);*/
        val decorView = window.decorView
        val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        decorView.systemUiVisibility = option
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}

fun getStatusBarHeight(context: Context): Int {
    // 获得状态栏高度
    val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    return context.resources.getDimensionPixelSize(resourceId)
}

//字体相关

val TYPEFACE_CACHE = SimpleArrayMap<String, WeakReference<Typeface>>()


private fun getTypefacePath(fontType: Int): String? {
    var typefacePath: String? = null
    when (fontType) {
        1 -> typefacePath = "fonts/DIN-Bold.otf"
        2 -> typefacePath = "fonts/DIN-Medium.otf"
        3 -> typefacePath = "fonts/DIN-Regular.otf"
        4 -> typefacePath = "fonts/LilyScriptOne-Regular.ttf"
        else -> {
        }
    }
    return typefacePath
}

fun getTypeface(context: Context, fontType: Int): Typeface? {
    val typefacePath = getTypefacePath(fontType)
    var typeface: Typeface? = null
    if (TYPEFACE_CACHE.containsKey(typefacePath)) {
        val typefaceWr = TYPEFACE_CACHE.get(typefacePath)
        if (typefaceWr != null) {
            typeface = typefaceWr.get()
        }
    }
    if (typeface == null) {
        typeface = Typeface.createFromAsset(context.assets, typefacePath)
        TYPEFACE_CACHE.put(typefacePath, WeakReference(typeface))
    }
    return typeface
}

class SpacesItemDecoration(var mContext: Context, var space: Int, var drawableId: Int) :
    RecyclerView.ItemDecoration() {

    val mDrawable = ContextCompat.getDrawable(this.mContext, drawableId)
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = space
        outRect.right = space
        outRect.bottom = space
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        for (i in 0 until parent.childCount) {
            drawHorizontalDecoration(c, parent.getChildAt(i))
        }
    }

    private fun drawHorizontalDecoration(c: Canvas, childAt: View) {
        val rect = Rect(0, 0, 0, 0)
        rect.top = childAt.bottom
        mDrawable?.let {
            rect.bottom = rect.top + space
            rect.left = childAt.left
            rect.right = childAt.right
            it.bounds = rect
            it.draw(c)
        }

    }
}

//加载框
class StarterCommon(var activity: Activity?) {
    private var mProgressLoading: ProgressLoading? = null
    private var mUnBackProgressLoading: ProgressLoading? = null
    var isProgressShow: Boolean = false
        private set


    fun onDestroy() {
        mProgressLoading = null
        mUnBackProgressLoading = null
        activity = null
    }

    private fun isFinishing(): Boolean {
        return activity == null || activity!!.isFinishing
    }


    fun showProgressLoading(resId: Int) {
        if (!isFinishing()) {
            showProgressLoading(activity!!.getString(resId))
        }
    }

    fun showProgressLoading(text: String) {
        if (mProgressLoading == null) {
            mProgressLoading = ProgressLoading(activity!!, R.style.ProgressLoadingTheme)
            mProgressLoading!!.setCanceledOnTouchOutside(true)
            mProgressLoading!!.setOnCancelListener(DialogInterface.OnCancelListener {
                isProgressShow = false
            })
        }
        if (!TextUtils.isEmpty(text)) {
            mProgressLoading!!.mText = text
        } else {
            mProgressLoading!!.mText = ""
        }
        isProgressShow = true
        mProgressLoading!!.show()
    }

    fun dismissProgressLoading() {
        if (mProgressLoading != null && !isFinishing()) {
            isProgressShow = false
            mProgressLoading!!.dismiss()
        }
    }

    fun showUnBackProgressLoading(resId: Int) {
        showUnBackProgressLoading(activity!!.getString(resId))
    }

    // 按返回键不可撤销的
    fun showUnBackProgressLoading(text: String) {
        if (mUnBackProgressLoading == null) {
            mUnBackProgressLoading =
                object : ProgressLoading(activity!!, R.style.ProgressLoadingTheme) {
                    override fun onBackPressed() {
                        super.onBackPressed()
                    }
                }
        }
        if (!TextUtils.isEmpty(text)) {
            mUnBackProgressLoading!!.mText = text
        } else {
            mUnBackProgressLoading!!.mText = ""
        }

        mUnBackProgressLoading!!.show()
    }

    fun dismissUnBackProgressLoading() {
        if (mUnBackProgressLoading != null && !isFinishing()) {
            mUnBackProgressLoading!!.dismiss()
        }
    }
}
