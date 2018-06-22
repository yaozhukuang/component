package com.zw.yzk.component.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanwei
 */
public abstract class AbstractRecyclerViewAdapter<T, H extends ViewHolder> extends RecyclerView.Adapter<H> {

    private List<T> dataList;
    private int layoutId;
    protected Context context;

    private HeaderManager headerManager = new HeaderManager();
    private FooterManager footerManager = new FooterManager();
    private EmptyViewManager emptyViewManager = new EmptyViewManager();
    private LoadMoreManager loadMoreManager = new LoadMoreManager();

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private boolean loadMoreEnable;

    public AbstractRecyclerViewAdapter(List<T> dataList, @LayoutRes int layoutId) {
        this.dataList = dataList;
        this.layoutId = layoutId;
    }

    public AbstractRecyclerViewAdapter(@LayoutRes int layoutId) {
        this.dataList = new ArrayList<>();
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public H onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        switch (viewType) {
            case AdapterConstant.VIEW_TYPE_EMPTY:
                return createViewHolder(emptyViewManager.getEmptyView());
            case AdapterConstant.VIEW_TYPE_HEADER:
                return createViewHolder(headerManager.getHeaderView());
            case AdapterConstant.VIEW_TYPE_FOOTER:
                return createViewHolder(footerManager.getFooterView());
            case AdapterConstant.VIEW_TYPE_LOADING:
                return createViewHolder(loadMoreManager.getLoadMoreView().getLoadingView());
            default:
                return createViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull H holder, int position) {
        switch (getItemViewType(position)) {
            case AdapterConstant.VIEW_TYPE_EMPTY:
            case AdapterConstant.VIEW_TYPE_HEADER:
            case AdapterConstant.VIEW_TYPE_FOOTER:
                return;
            case AdapterConstant.VIEW_TYPE_LOADING:
                loading();
                return;
            default:
                bind(holder, dataList.get(getDataPosition(position)));
        }
    }

    @Override
    public int getItemCount() {
        int dataCount = getDataCount();
        if (dataCount == 0) {
            return headerManager.getHeaderCount() + footerManager.getFooterCount()
                    + emptyViewManager.geEmptyViewCount();
        } else {
            return headerManager.getHeaderCount() + footerManager.getFooterCount()
                    + dataCount + loadMoreManager.getLoadMoreCount();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getDataCount() == 0) {
            if (position < headerManager.getHeaderCount()) {
                return AdapterConstant.VIEW_TYPE_HEADER;
            } else if (position >= headerManager.getHeaderCount()
                    && position < headerManager.getHeaderCount() + emptyViewManager.geEmptyViewCount()) {
                return AdapterConstant.VIEW_TYPE_EMPTY;
            } else {
                return AdapterConstant.VIEW_TYPE_FOOTER;
            }
        }
        if (position < headerManager.getHeaderCount()) {
            return AdapterConstant.VIEW_TYPE_HEADER;
        } else if (position >= getItemCount() - loadMoreManager.getLoadMoreCount() - footerManager.getFooterCount()
                && position < getItemCount() - loadMoreManager.getLoadMoreCount()) {
            return AdapterConstant.VIEW_TYPE_FOOTER;
        } else if (position == getItemCount() - loadMoreManager.getLoadMoreCount()) {
            return AdapterConstant.VIEW_TYPE_LOADING;
        } else {
            return AdapterConstant.VIEW_TYPE_ITEM;
        }
    }

    /**
     * 获取header
     * @return 返回header
     */
    public View getHeader() {
        return headerManager.getHeaderView();
    }

    /**
     * 获取footer
     * @return footer
     */
    public View getFooter() {
        return footerManager.getFooterView();
    }

    /**
     * 返回item个数，由于可能存在header、footer、loading 所以不要使用
     * getItemCount返回数据的个数
     *
     * @return item的个数（不包含header，footer，loading）
     */
    public int getDataCount() {
        return dataList == null ? 0 : dataList.size();
    }

    /**
     * 返回item的实际位置
     *
     * @param position 包含了header，footer，loading的位置
     * @return item的实际位置
     */
    public int getDataPosition(int position) {
        return position - headerManager.getHeaderCount();
    }

    /**
     * 判断当前是否处于下拉刷新状态
     *
     * @return true：正在刷新，false：不处于刷新状态
     */
    public boolean isRefreshing() {
        return refreshLayout != null && refreshLayout.isRefreshing();
    }

    /**
     * 判断当前是否处于上拉加载装
     *
     * @return true: 正在加载， false：不处于加载状态
     */
    public boolean isLoading() {
        return loadMoreManager.getLoadState() == AdapterConstant.LOAD_STATE_LOADING;
    }

    /**
     * 下拉或者上拉刷新失败
     */
    public void finishFailed() {
        if (isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        if (isLoading()) {
            loadFailed();
        }
    }

    /**
     * 设置数据列表
     *
     * @param data 列表数据
     */
    public void setData(List<T> data) {
        if (data != null) {
            this.dataList = data;
            notifyDataSetChanged();
        }
    }

    /**
     * 添加一条数据
     *
     * @param item item数据
     */
    public void add(T item) {
        int start = headerManager.getHeaderCount() + dataList.size();
        this.dataList.add(item);
        notifyItemInserted(start);
    }

    /**
     * 添加一列数据
     *
     * @param data 数据列表
     */
    public void add(List<T> data) {
        if (data != null) {
            int start = headerManager.getHeaderCount() + dataList.size();
            this.dataList.addAll(data);
            notifyItemRangeInserted(start, data.size());
        }
    }

    /**
     * 清空列表
     */
    public void clear() {
        if (dataList.isEmpty()) {
            return;
        }
        dataList.clear();
        notifyDataSetChanged();
    }

    /**
     * 设置SwipeRefreshLayout用于下拉刷新
     *
     * @param refreshLayout 包裹RecyclerView的SwipeRefreshLayout
     */
    public AbstractRecyclerViewAdapter swipeRefreshLayout(SwipeRefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;

        return this;
    }

    /**
     * 设置下拉刷新是否可用，当设置了swipeRefreshLayout后默认可用
     *
     * @param enable: true 可用，false 不可用
     */
    public AbstractRecyclerViewAdapter setRefreshEnable(boolean enable) {
        if (refreshLayout == null) {
            throw new RuntimeException("mehod swipeRefreshLayout must be called first");
        }
        refreshLayout.setEnabled(enable);

        return this;
    }

    /**
     * 设置当前刷新状态,处于刷新状态时不能上拉加载
     *
     * @param refreshing true:处于刷新状态，false：不处于刷新状态
     */
    public void setRefreshing(boolean refreshing) {
        if (refreshLayout == null) {
            throw new RuntimeException("mehod swipeRefreshLayout must be called first");
        }
        refreshLayout.setRefreshing(refreshing);
        if (loadMoreEnable && !refreshing) {
            loadMoreManager.setLoadMoreEnable(true);
            notifyItemInserted(getItemCount() - loadMoreManager.getLoadMoreCount());
        }
        setLoadViewState(AdapterConstant.LOAD_STATE_DEFAULT);
    }

    /**
     * 设置下拉刷新监听事件
     *
     * @param listener 监听事件
     */
    public AbstractRecyclerViewAdapter setRefreshListener(final RefreshListener listener) {
        if (refreshLayout == null) {
            throw new RuntimeException("method swipeRefreshLayout must be called first");
        }
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (loadMoreEnable) {
                    notifyItemRemoved(getItemCount() - loadMoreManager.getLoadMoreCount());
                    loadMoreManager.setLoadMoreEnable(false);
                }
                listener.onRefresh();
            }
        });

        return this;
    }

    /**
     * 将adapter添加到RecyclerView
     *
     * @param view 待添加的列表
     */
    public AbstractRecyclerViewAdapter with(RecyclerView view) {
        this.recyclerView = view;

        return this;
    }

    /**
     * 设置列表header
     *
     * @param header header
     */
    public AbstractRecyclerViewAdapter header(View header) {
        headerManager.setHeaderView(header);

        return this;
    }

    /**
     * 设置列表footer
     *
     * @param footer footer
     */
    public AbstractRecyclerViewAdapter footer(View footer) {
        footerManager.setFooterView(footer);

        return this;
    }

    /**
     * 设置EmptyView
     *
     * @param empty 空列表时展示的视图
     */
    public AbstractRecyclerViewAdapter empty(View empty) {
        emptyViewManager.setEmptyView(empty);

        return this;
    }

    /**
     * 设置加载更多View
     *
     * @param view LoadMoreView的实现
     */
    public AbstractRecyclerViewAdapter loadMore(LoadMoreView view) {
        loadMoreManager.setLoadMoreView(view);
        setLoadMoreEnable(true);

        return this;
    }

    /**
     * 设置是否允许加载更多
     *
     * @param enable loadMoreEnable true： 允许，false：不允许
     */
    public AbstractRecyclerViewAdapter setLoadMoreEnable(boolean enable) {
        loadMoreManager.setLoadMoreEnable(enable);
        loadMoreEnable = enable;

        return this;
    }

    /**
     * 设置加载更多监听事件
     *
     * @param listener 加载更多监听事件
     */
    public AbstractRecyclerViewAdapter setLoadMoreListener(LoadMoreListener listener) {
        loadMoreManager.setLoadMoreListener(listener);
        setLoadMoreEnable(true);

        return this;
    }

    /**
     * 完成基本动作（设置spanlookup、默认加载视图等）
     */
    public void build() {
        if (recyclerView == null) {
            throw new RuntimeException("RecyclerView must be set first");
        }
        recyclerView.setAdapter(this);
        if (loadMoreManager.isLoadMoreEnable() && loadMoreManager.getLoadMoreView() == null) {
            loadMoreManager.setLoadMoreView(new DefaultLoadMoreView(recyclerView.getContext()));
        }
        setSpanSize();
    }

    /**
     * 加载成功
     */
    public void loadSucceed() {
        if (loadMoreManager.getLoadMoreView() == null) {
            return;
        }
        loadMoreManager.getLoadMoreView().loading();
        setLoadViewState(AdapterConstant.LOAD_STATE_DEFAULT);
    }

    /**
     * 加载失败
     */
    public void loadFailed() {
        if (loadMoreManager.getLoadMoreView() == null) {
            return;
        }
        loadMoreManager.getLoadMoreView().loadFailed();
        setLoadViewState(AdapterConstant.LOAD_STATE_FAILED);
    }

    /**
     * 已经加载所有数据
     */
    public void loadAll() {
        if (loadMoreManager.getLoadMoreView() == null) {
            return;
        }
        loadMoreManager.getLoadMoreView().loadComplete();
        setLoadViewState(AdapterConstant.LOAD_STATE_COMPLETE);
    }

    /**
     * 设置列表每列item数目，当列表是网格布局的时候header、footer、LoadMore都要独占一行
     */
    private void setSpanSize() {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager == null) {
            throw new IllegalArgumentException("RecyclerView must set LayoutManager first");
        }
        if (manager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(
                    new WrapperSpanSizeLookup(gridLayoutManager.getSpanSizeLookup(),
                            gridLayoutManager.getSpanCount(), this));
        }
    }

    /**
     * 正在加载
     */
    private void loading() {
        if (loadMoreManager.getLoadState() == AdapterConstant.LOAD_STATE_DEFAULT) {
            loadMoreManager.getLoadMoreView().loading();
            loadMoreManager.setLoadState(AdapterConstant.LOAD_STATE_LOADING);
            loadMoreManager.getLoadMoreListener().onLoad();

            //上拉时不能下拉
            if (refreshLayout != null) {
                refreshLayout.setEnabled(false);
            }
        }
    }

    /**
     * 设置加载更多View的状态
     *
     * @param state View状态
     */
    private void setLoadViewState(int state) {
        loadMoreManager.setLoadState(state);
        if (refreshLayout != null && state != AdapterConstant.LOAD_STATE_LOADING) {
            refreshLayout.setEnabled(true);
        }
    }

    /**
     * adapter逻辑处理
     *
     * @param holder view holder
     * @param item   data数据
     */
    protected abstract void bind(H holder, T item);

    /**
     * 创建ViewHolder，如果H是继承ViewHoder则需要重写这个方法
     */
    @SuppressWarnings("unchecked")
    protected H createViewHolder(View view) {
        return (H) new ViewHolder(view);
    }


}
