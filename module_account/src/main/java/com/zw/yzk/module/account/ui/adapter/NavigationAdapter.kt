package com.zw.yzk.module.account.ui.adapter


import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.zw.yzk.component.adapter.ViewHolder
import com.zw.yzk.component.common.base.BaseAdapter
import com.zw.yzk.component.common.utils.IntentUtils
import com.zw.yzk.module.account.R
import com.zw.yzk.module.account.data.Navigation
import com.zw.yzk.module.account.data.NavigationGroup

class NavigationAdapter(layoutId: Int = R.layout.module_account_li_navigation_group) :
        BaseAdapter<NavigationGroup, NavigationAdapter.Holder>(layoutId) {

    override fun bind(holder: Holder, item: NavigationGroup) {
        holder.apply {
            get<TextView>(R.id.title).text = item.name
            setChildren(item.children, context)
        }
    }

    override fun createViewHolder(view: View): Holder {
        return Holder(view)
    }


    class Holder(itemView: View) : ViewHolder(itemView) {
        private val list = itemView.findViewById<RecyclerView>(R.id.children)
        private val childAdapter = ChildAdapter()

        fun setChildren(children: List<Navigation>, context: Context) {
            if (childAdapter.dataCount == 0) {
                list.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
                childAdapter.with(list).build()
            }
            childAdapter.setData(children)
        }
    }

    class ChildAdapter(layoutId: Int = R.layout.module_account_li_navigation_child) :
            BaseAdapter<Navigation, ViewHolder>(layoutId) {

        override fun bind(holder: ViewHolder, item: Navigation) {

            (holder.itemView as TextView).text = item.title
            holder.itemView.setOnClickListener {
                IntentUtils.startBroswer(context, item.link)
            }
        }

    }
}