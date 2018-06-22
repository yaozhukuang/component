package com.zw.yzk.component.adapter;

import android.support.v7.widget.GridLayoutManager;

public class WrapperSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private GridLayoutManager.SpanSizeLookup spanSizeLookup;
    private AbstractRecyclerViewAdapter adapter;
    private int spanCount;

    public WrapperSpanSizeLookup(GridLayoutManager.SpanSizeLookup spanSizeLookup,
                                 int spanCount,
                                 AbstractRecyclerViewAdapter adapter) {
        this.spanSizeLookup = spanSizeLookup;
        this.adapter = adapter;
        this.spanCount = spanCount;
    }

    @Override
    public int getSpanSize(int position) {
        if (adapter.getItemViewType(position) == AdapterConstant.VIEW_TYPE_ITEM) {
            return spanSizeLookup.getSpanSize(position);
        } else {
            return spanCount;
        }
    }
}