package com.zw.yzk.module.article.ui.adapter

import android.widget.TextView
import com.zw.yzk.component.common.base.BaseAdapter
import com.zw.yzk.component.adapter.ViewHolder
import com.zw.yzk.component.common.base.BaseWebActivity
import com.zw.yzk.module.article.R
import com.zw.yzk.module.article.data.Article

/**
 * 文章列表adapter
 */
class ArticleAdapter(layoutId: Int = R.layout.module_article_li_acrtilce)
    : BaseAdapter<Article, ViewHolder>(layoutId) {

    override fun bind(holder: ViewHolder, item: Article) {
        holder.apply {
            get<TextView>(R.id.name).text = item.author
            get<TextView>(R.id.title).text = item.title
            get<TextView>(R.id.description).text = item.des
            get<TextView>(R.id.type).text = item.type
            get<TextView>(R.id.date).text = item.date

            itemView.setOnClickListener {
                BaseWebActivity.startBaseWebActivity(context, item.title, item.link)
            }
        }
    }
}