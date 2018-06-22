package com.zw.yzk.component.common.utils

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.zw.yzk.component.common.R

/**
 * toolbar
 */
class ToolbarBuilder private constructor(private val context: AppCompatActivity) {

    private val toolBar: Toolbar = context.findViewById(R.id.toolBar)
    private val title: TextView
    private val rightText: TextView

    init {
        toolBar.title = ""
        context.setSupportActionBar(toolBar)
        title = toolBar.findViewById(R.id.toolbarTitle)
        rightText = toolBar.findViewById(R.id.toolBarRightText)
    }

    companion object {
        fun builder(context: AppCompatActivity): ToolbarBuilder = ToolbarBuilder(context)
    }

    fun setTitle(title: String): ToolbarBuilder {
        this.title.text = title
        return this
    }

    fun setTitle(title: Int): ToolbarBuilder {
        this.title.setText(title)
        return this
    }

    fun setTtitleColor(color: Int): ToolbarBuilder {
        this.title.setTextColor(color)
        return this
    }

    fun setNavigationIcon(res: Int): ToolbarBuilder {
        this.toolBar.setNavigationIcon(res)
        return this
    }

    fun setBackgroundColor(color: Int): ToolbarBuilder {
        this.toolBar.setBackgroundColor(color)
        return this
    }

    fun setRightText(res: Int): ToolbarBuilder {
        this.rightText.text = context.resources.getString(res)
        this.rightText.visibility = View.VISIBLE
        return this
    }

    fun setRightText(text: String): ToolbarBuilder {
        this.rightText.text = text
        this.rightText.visibility = View.VISIBLE
        return this
    }

    fun setRightTextColor(color: Int): ToolbarBuilder {
        this.rightText.setTextColor(color)
        this.rightText.visibility = View.VISIBLE
        return this
    }

    fun setRightText(res: Int, listener: View.OnClickListener): ToolbarBuilder {
        this.rightText.text = context.resources.getString(res)
        this.rightText.setOnClickListener(listener)
        this.rightText.visibility = View.VISIBLE
        return this
    }

    fun setRightText(text: String, listener: View.OnClickListener): ToolbarBuilder {
        this.rightText.text = text
        this.rightText.setOnClickListener(listener)
        this.rightText.visibility = View.VISIBLE
        return this
    }

    fun build(): Toolbar {
        this.toolBar.setNavigationOnClickListener({
            context.finish()
        })
        return toolBar
    }


}