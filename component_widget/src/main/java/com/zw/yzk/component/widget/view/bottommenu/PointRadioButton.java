package com.zw.yzk.component.widget.view.bottommenu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

/**
 * @author zhanwei
 */
public class PointRadioButton extends AppCompatRadioButton {

    private boolean showRedPoint;
    private int redCount;
    private float pRadius;
    private float cBg;
    private Paint paint;

    public PointRadioButton(Context context) {
        this(context, null);
    }

    public PointRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PointRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    /**
     * 显示小红点
     */
    public void showRedPoint() {
        showRedPoint = true;
        invalidate();
    }

    /**
     * 隐藏小红点
     */
    public void dismissRedPoint() {
        showRedPoint = false;
        invalidate();
    }

    /**
     * 显示未读条目
     */
    public void showRedCount(int count) {
        redCount = count;
        invalidate();
    }

    /**
     * 隐藏维度条目
     */
    public void dismissRedCount() {
        redCount = -1;
        invalidate();
    }

    /**
     * 初始化操作
     */
    private void init() {
        float density = getContext().getResources().getDisplayMetrics().density;
        //默认红点半径
        pRadius = density * 5;
        //默认未读条目北京半径
        cBg = density * 8;
        //绘制红点的画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(density * 12);
        //初始不绘制
        redCount = -1;
        showRedPoint = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (redCount > 0) {
            showRedPoint = false;
            drawRedCount(canvas);
        } else {
            drawRedPoint(canvas);
        }

    }

    /**
     * 绘制小红点
     */
    private void drawRedPoint(Canvas canvas) {
        if (!showRedPoint) {
            return;
        }
        paint.setColor(Color.RED);
        canvas.drawCircle(getWidth() - pRadius * 2, pRadius * 2, pRadius, paint);
    }

    /**
     * 绘制未读条目
     */
    private void drawRedCount(Canvas canvas) {
        float width = getWidth();
        //将位置向左下平移pRadius，避免贴边
        paint.setColor(Color.RED);
        canvas.drawCircle(width - cBg - pRadius, cBg + pRadius, cBg, paint);
        paint.setColor(Color.WHITE);
        String text = String.valueOf(redCount);
        float textWidth = paint.measureText(text);
        Paint.FontMetrics metrics = paint.getFontMetrics();
        float startY = (metrics.descent - metrics.ascent) / 2;
        canvas.drawText(text, width - cBg - textWidth / 2 - pRadius, cBg / 2 + startY + pRadius, paint);
    }
}
