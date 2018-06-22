package com.zw.yzk.module.search.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexboxLayout
import com.zw.yzk.component.common.base.BasePresenterActivity
import com.zw.yzk.component.common.utils.LogUtils
import com.zw.yzk.module.search.R
import com.zw.yzk.module.search.data.HotWord
import com.zw.yzk.module.search.data.Search
import com.zw.yzk.module.search.presenter.SearchPresenter
import com.zw.yzk.module.search.ui.adapter.SearchAdapter
import com.zw.yzk.module.search.ui.view.SearchView
import com.zw.yzk.module.search.ui.widget.HotWordItem
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.module_search_activity_search.*
import java.util.concurrent.TimeUnit

class SearchActivity : BasePresenterActivity<SearchPresenter>(), SearchView {

    private val adapter = SearchAdapter()

    private val colors = listOf(
            Color.parseColor("#4DCCF6"),
            Color.parseColor("#FF9999"),
            Color.parseColor("#999933"),
            Color.parseColor("#009999"),
            Color.parseColor("#FF9900"))

    override fun getLayoutId() = R.layout.module_search_activity_search

    override fun initPresenter() {
        presenter = SearchPresenter()
        presenter?.setView(this)
    }

    override fun initToolbar() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        setListener()
        getHotWord()
    }

    override fun setResultList(list: List<Search>) {
        if (adapter.isRefreshing) {
            adapter.setData(list)
            adapter.isRefreshing = false
        } else {
            if (list.isEmpty()) {
                adapter.loadAll()
            } else {
                adapter.add(list)
                adapter.loadSucceed()
            }
        }
        if (adapter.dataCount == 0) {
            refreshLayout.visibility = View.GONE
            layoutHotWord.visibility = View.VISIBLE
        } else {
            refreshLayout.visibility = View.VISIBLE
            layoutHotWord.visibility = View.GONE
        }
    }

    override fun searchFailed() {
        adapter.finishFailed()
    }

    override fun setHotWord(list: List<HotWord>) {
        val margin = (resources.displayMetrics.density * 6).toInt()
        list.forEachIndexed { index, hotWord ->
            val item = createItem(colors[index % colors.size], margin)
            item.text = hotWord.name
            hotContainer.addView(item)
        }
    }

    /**
     * 初始化RecyclerView
     */
    private fun initRecyclerView() {
        resultList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        adapter.swipeRefreshLayout(refreshLayout)
                .with(resultList)
                .empty(LayoutInflater.from(this).inflate(R.layout.module_search_empty_search_result, resultList, false))
                .setRefreshListener {
                    searchWord(search.text.toString(), 0)
                }
                .setLoadMoreListener {
                    searchWord(search.text.toString(), adapter.dataCount)
                }
                .build()
    }

    /**
     * 设置监听事件
     */
    private fun setListener() {
        //用户输入监听事件
        Observable.create(ObservableOnSubscribe<String> {
            search.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    it.onNext(s.toString().trim())
                }
            })
        }).debounce(200, TimeUnit.MILLISECONDS)
                .subscribe {
                    if (it.isEmpty()) {
                        setResultList(ArrayList())
                    } else {
                        searchWord(it, 0)
                    }
                }
        //取消按钮监听事件
        cancel.setOnClickListener {
            finish()
        }
    }

    /**
     * 生成热词item
     */
    private fun createItem(color: Int, margin: Int): HotWordItem {
        return HotWordItem(this, color).apply {
            layoutParams = FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                leftMargin = margin
                rightMargin = margin
                topMargin = margin * 2
            }
            gravity = Gravity.CENTER
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f)
            setTextColor(Color.WHITE)
            setOnClickListener {
                search.setText(text)
            }
        }
    }

    /**
     * 获取热词
     */
    private fun getHotWord() {
        presenter?.getHotWord()
    }

    /**
     * 搜索热词
     */
    private fun searchWord(key: String, index: Int) {
        LogUtils.d("searchWord time: " + System.currentTimeMillis() / 1000)
        presenter?.search(key, index)
    }
}