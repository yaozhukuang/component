package com.zw.yzk.module.account.ui.adapter

import android.widget.TextView
import com.zw.yzk.component.adapter.ViewHolder
import com.zw.yzk.component.common.base.BaseAdapter
import com.zw.yzk.component.common.base.BaseWebActivity
import com.zw.yzk.module.account.R
import com.zw.yzk.module.account.data.Article
import com.zw.yzk.module.account.data.ArticleEntity

class ArticleAdapter(layoutId: Int = R.layout.module_account_li_article): BaseAdapter<Article, ViewHolder>(layoutId) {

    override fun bind(holder: ViewHolder, item: Article) {
        holder.apply {
            get<TextView>(R.id.name).text = item.author
            get<TextView>(R.id.title).text = item.title
            get<TextView>(R.id.date).text = item.niceDate
            get<TextView>(R.id.type).text = item.chapterName

            itemView.setOnClickListener {
                BaseWebActivity.startBaseWebActivity(context, item.title, item.link)
            }
        }
    }
}