package com.zw.yzk.module.search.ui.adapter

import android.widget.TextView
import com.zw.yzk.component.adapter.ViewHolder
import com.zw.yzk.component.common.base.BaseAdapter
import com.zw.yzk.component.common.base.BaseWebActivity
import com.zw.yzk.module.search.R
import com.zw.yzk.module.search.data.Search

class SearchAdapter(layoutId: Int = R.layout.module_search_li_search_result):
        BaseAdapter<Search, ViewHolder>(layoutId) {
    override fun bind(holder: ViewHolder, item: Search) {
        holder.apply {
            get<TextView>(R.id.title).text = item.title
            get<TextView>(R.id.name).text = item.author
            get<TextView>(R.id.type).text = item.type
            get<TextView>(R.id.date).text = item.date

            itemView.setOnClickListener {
                BaseWebActivity.startBaseWebActivity(context, item.title, item.link)
            }
        }
    }
}