package com.zw.yzk.component.adapter;

public class AdapterConstant {

    /**
     * 视图类型是header
     */
    static final int VIEW_TYPE_HEADER = 10001;
    /**
     * 视图类型是footer
     */
    static final int VIEW_TYPE_FOOTER = 10002;
    /**
     * 视图类型是加载
     */
    static final int VIEW_TYPE_LOADING = 10003;
    /**
     * 视图类型是普通item
     */
    static final int VIEW_TYPE_ITEM = 10004;
    /**
     * 视图时空列表视图
     */
    static final int VIEW_TYPE_EMPTY = 10005;


    /**
     * 初始状态、本次加载完成且还有可加载项
     */
    static final int LOAD_STATE_DEFAULT = 20001;
    /**
     * 正在加载状态
     */
    static final int LOAD_STATE_LOADING = 20002;
    /**
     * 加载失败
     */
    static final int LOAD_STATE_FAILED = 20003;
    /**
     * 全部加载完成
     */
    static final int LOAD_STATE_COMPLETE = 20004;

}
