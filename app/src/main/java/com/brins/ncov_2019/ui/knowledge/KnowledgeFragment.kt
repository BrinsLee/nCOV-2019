package com.brins.ncov_2019.ui.knowledge


import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.brins.ncov_2019.R
import com.brins.ncov_2019.model.KnowledgeResult
import com.brins.ncov_2019.ui.activity.BaseActivity
import com.brins.ncov_2019.ui.activity.WebActivity
import com.brins.ncov_2019.ui.adapter.CommonViewAdapter
import com.brins.ncov_2019.ui.adapter.ViewHolder
import com.brins.ncov_2019.ui.base.BaseFragment
import com.brins.ncov_2019.ui.widget.CornersTransform
import com.brins.ncov_2019.utils.*
import kotlinx.android.synthetic.main.fragment_knowledge.*

class KnowledgeFragment : BaseFragment() {

    private lateinit var mAdapter: CommonViewAdapter<KnowledgeResult.Result>
    private var mList = arrayListOf<KnowledgeResult.Result>()
    private val knowledgeViewModel by lazy {
        ViewModelProvider(
            this,
            InjectorUtil.getKnowledgeModelFactory()
        ).get(KnowledgeViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_knowledge
    }

    override fun initEventAndData() {
        knowledgeViewModel.metaWiki.observe(this, Observer<KnowledgeResult> {
            it.result?.let { it1 ->
                mList.addAll(it1)
            }
            if (mList.size != 0) {
                mAdapter = object : CommonViewAdapter<KnowledgeResult.Result>(
                    mContext,
                    R.layout.adapter_knowledge_item,
                    mList
                ) {
                    override fun converted(
                        holder: ViewHolder,
                        t: KnowledgeResult.Result,
                        position: Int
                    ) {
                        holder.getView<ConstraintLayout>(R.id.wiki_container_cl).setOnClickListener{
                            WebActivity.startThis(mContext as BaseActivity, t.linkUrl)
                        }
                        holder.setText(R.id.wiki_title_tv, t.title)
                        holder.setText(R.id.wiki_content_tv, t.description)
                        t.imgUrl?.let {
                            ImageLoadreUtils.getInstance().loadImage(
                                mContext, ImageLoader.Builder().assignHeight(500).assignWidth(500)
                                    .url(it).imgView(holder.getView<ImageView>(R.id.wiki_cover_iv)).bitmapTransformation(
                                        CornersTransform(mContext, dp2px(5f).toFloat())
                                    ).bulid()
                            )
                        }
                    }

                }
                recycler_wiki.adapter = mAdapter
                recycler_wiki.layoutManager = LinearLayoutManager(mContext)
                recycler_wiki.addItemDecoration(
                    SpacesItemDecoration(
                        mContext,
                        5,
                        R.color.white1_80
                    )
                )
            }
        })
        knowledgeViewModel.fetchRumors()
    }

}
