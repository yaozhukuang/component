package com.zw.yzk.module.project.com.zw.yzk.module.project.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.zw.yzk.component.adapter.ViewHolder
import com.zw.yzk.component.common.base.BaseAdapter
import com.zw.yzk.component.common.base.BaseWebActivity
import com.zw.yzk.component.common.image.ImageLoader
import com.zw.yzk.module.project.R
import com.zw.yzk.module.project.data.Project

class ProjectListAdapter(layoutId: Int = R.layout.module_project_li_project)
    : BaseAdapter<Project, ViewHolder>(layoutId) {

    override fun bind(holder: ViewHolder, item: Project) {
        holder.get<TextView>(R.id.title).text = item.title
        holder.get<TextView>(R.id.description).text = item.des
        holder.get<TextView>(R.id.name).text = item.author
        holder.get<TextView>(R.id.date).text = item.date
        ImageLoader.load(context, item.image, holder.get(R.id.image))

        holder.get<View>(R.id.more).setOnClickListener {
            BaseWebActivity.startBaseWebActivity(context, "Github", item.projectLink)
        }

        holder.itemView.setOnClickListener {
            BaseWebActivity.startBaseWebActivity(context, item.title, item.link)
        }
    }
}