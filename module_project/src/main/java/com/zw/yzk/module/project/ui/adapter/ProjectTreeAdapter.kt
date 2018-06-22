package com.zw.yzk.module.project.ui.adapter


import android.widget.TextView
import com.zw.yzk.component.adapter.ViewHolder
import com.zw.yzk.component.common.base.BaseAdapter
import com.zw.yzk.component.widget.toast.ToastManager
import com.zw.yzk.module.project.R
import com.zw.yzk.module.project.com.zw.yzk.module.project.ui.activity.ProjectListActivity
import com.zw.yzk.module.project.data.ProjectEntity

class ProjectTreeAdapter(layoutId: Int = R.layout.module_project_li_project_tree)
    : BaseAdapter<ProjectEntity, ViewHolder>(layoutId) {

    override fun bind(holder: ViewHolder, item: ProjectEntity) {
        holder.get<TextView>(R.id.type).text = item.name
        holder.itemView.setOnClickListener {
            ProjectListActivity.startProjectListActivity(context, item.id, item.name)
        }
    }
}