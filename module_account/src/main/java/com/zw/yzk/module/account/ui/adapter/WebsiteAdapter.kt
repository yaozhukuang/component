package com.zw.yzk.module.account.ui.adapter


import android.widget.TextView
import com.zw.yzk.component.adapter.ViewHolder
import com.zw.yzk.component.common.base.BaseAdapter
import com.zw.yzk.component.common.base.BaseWebActivity
import com.zw.yzk.component.common.utils.IntentUtils
import com.zw.yzk.module.account.R
import com.zw.yzk.module.account.data.Website


class WebsiteAdapter(layoutId: Int = R.layout.module_account_li_website) : BaseAdapter<Website, ViewHolder>(layoutId) {

    override fun bind(holder: ViewHolder, item: Website) {
        holder.apply {
            get<TextView>(R.id.name).text = item.name
            get<TextView>(R.id.website).text = item.link

            itemView.setOnClickListener {
                IntentUtils.startBroswer(context, item.link)
            }
        }
    }
}