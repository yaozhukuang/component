package com.zw.yzk.component.widget.view.bottommenu;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanwei
 * 底部菜单
 */
public class BottomMenu extends RadioGroup {

    private ColorStateList colorStateList;
    private List<PointRadioButton> list;

    public BottomMenu(Context context) {
        this(context, null);
    }

    public BottomMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        setOrientation(RadioGroup.HORIZONTAL);
        list = new ArrayList<>();
    }

    /**
     * 获取指定的item
     */
    public PointRadioButton getItem(int index) {
        return list.get(index);
    }

    /**
     * 指定点击某个item
     */
    public void setChecked(int index) {
        if (index < list.size()) {
            list.get(index).performClick();
        }
    }

    /**
     * 设置文字的颜色
     */
    public BottomMenu setTextColor(int upColor, int downColor) {
        int[][] states = new int[][]{{ android.R.attr.state_checked}};
        int[] colors = new int[] {downColor, upColor};
        colorStateList = new ColorStateList(states, colors);

        return this;
    }

    /**
     * 设置文字的颜色
     */
    public BottomMenu setTextColor(int colorResId) {
        colorStateList = ContextCompat.getColorStateList(getContext(), colorResId);

        return this;
    }

    /**
     * 添加item
     */
    public BottomMenu add(String text, int drawableId) {
        return add(text, 10, drawableId);
    }

    /**
     * 添加item
     */
    public BottomMenu add(String text, float textSize, int drawableId) {
        PointRadioButton button = createItem();
        setItemText(button, text);
        setItemDrawable(button, drawableId);
        setItemTextSize(button, textSize);

        return add(button);
    }

    /**
     * 添加item
     */
    public BottomMenu add(PointRadioButton button) {
        list.add(button);
        return this;
    }

    /**
     * 设置item文字
     */
    public void setItemText(PointRadioButton button, String text) {
        button.setText(text);
    }

    /**
     * 设置item图片
     */
    public void setItemDrawable(PointRadioButton button, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), drawableId);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        button.setCompoundDrawables(null, drawable, null, null);
        button.setGravity(Gravity.CENTER);
        button.setCompoundDrawablePadding(0);
    }

    /**
     * 设置item字体大小
     */
    public void setItemTextSize(PointRadioButton button, float textSize) {
        button.setTextSize(textSize);
    }

    /**
     * 设置item文字颜色
     */
    public void setItemTextColor(PointRadioButton button, ColorStateList colorStateList) {
        button.setTextColor(colorStateList);
    }

    /**
     * 生成item
     */
    public PointRadioButton createItem() {
        PointRadioButton button = new PointRadioButton(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        button.setLayoutParams(params);
        button.setGravity(Gravity.CENTER);

        return button;
    }

    /**
     * 生成菜单
     */
    public void create() {
        for(int i = 0; i < list.size(); i++) {
            final PointRadioButton button = list.get(i);
            if(colorStateList != null) {
                setItemTextColor(button, colorStateList);
            }
            final int finalI = i;
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.onClick(button, finalI);
                    }
                }
            });
            addView(button);
        }
    }

    public interface OnItemClickedListener {
        /**
         * 菜单item点击事件
         *
         * @param item  被点击的item
         * @param index item索引
         */
        void onClick(PointRadioButton item, int index);
    }

    private OnItemClickedListener listener;

    /**
     * 设置菜单点击事件
     */
    public void setOnItemClickedListener(OnItemClickedListener listener) {
        this.listener = listener;
    }

}
