package com.zw.yzk.module.project.com.zw.yzk.module.project.ui.adapter

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class ProjectTreeItemDecoration(private val width: Int) : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null) {
            super.getItemOffsets(outRect, view, parent, state)
            return
        }
        val position = parent.layoutManager.getPosition(view)
        if (position == 0) {
            outRect.set(width, width, width, width)
            return
        }
        val spanCount = (parent.layoutManager as GridLayoutManager).spanCount
        val index = (position + 1) % spanCount
        when (index) {
            0 -> {
                outRect.set(width, 0, width / 2, width)
            }
            spanCount - 1 -> {
                outRect.set(width / 2, 0, width, width)
            }
            else -> {
                outRect.set(width / 2, 0, width / 2, width)
            }
        }
    }
}