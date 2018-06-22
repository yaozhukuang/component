package com.zw.yzk.module.account.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class MeBackground: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        paint.color = Color.parseColor("#E51C23")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        initPath(width.toFloat(), height.toFloat())
    }

    private fun initPath(fWidth: Float, fHeight: Float) {

        path.moveTo(0F, 0F)
        path.lineTo(fWidth, 0F)
        path.lineTo(fWidth, fHeight / 2)
        path.lineTo(0F,fHeight * 4 / 5)
        path.lineTo(0F, 0F)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.WHITE)
        canvas.drawPath(path, paint)
    }
}