package com.zw.yzk.module.search.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.TextView

class HotWordItem : TextView {

    private var padding: Int = 0
    private var color = 0

    constructor(context: Context?, color: Int) : super(context) {
        this.color = color
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        padding = (context.resources.displayMetrics.density * 8).toInt()
        setPadding(padding, padding / 4, padding, padding / 4)
    }

    override fun onDraw(canvas: Canvas) {
        drawBackground(canvas)
        super.onDraw(canvas)
    }

    private fun drawBackground(canvas: Canvas) {
        val radius = (height / 2).toFloat()
        val textColor = paint.color
        paint.color = color
        canvas.drawRoundRect(RectF(0F, 0F, width.toFloat(), height.toFloat()), radius, radius, paint)
        paint.color = textColor
    }

}