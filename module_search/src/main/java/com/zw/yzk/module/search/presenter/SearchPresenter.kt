package com.zw.yzk.module.search.presenter

import com.zw.yzk.component.common.base.BaseObserver
import com.zw.yzk.component.common.base.BaseTask
import com.zw.yzk.component.common.presenter.Presenter
import com.zw.yzk.component.network.usecase.DefaultUseCase
import com.zw.yzk.module.search.data.HotWordResponse
import com.zw.yzk.module.search.data.SearchResponse
import com.zw.yzk.module.search.data.SearchTask
import com.zw.yzk.module.search.repository.HotWordRepository
import com.zw.yzk.module.search.repository.SearchRepository
import com.zw.yzk.module.search.ui.view.SearchView

class SearchPresenter : Presenter {

    private var uSearch: DefaultUseCase<SearchTask>? = null
    private var uHotWord: DefaultUseCase<BaseTask>? = null
    private var searchView: SearchView? = null

    override fun onCreate() {
        uHotWord = DefaultUseCase(HotWordRepository())
    }

    override fun onDestroy() {
        uHotWord?.dispose()
        uSearch?.dispose()
    }

    fun setView(view: SearchView) {
        this.searchView = view
    }

    /**
     * 获取搜索热词
     */
    fun getHotWord() {
        uHotWord?.execute(HotWordObserver(searchView!!), BaseTask())
    }

    /**
     * 搜索
     */
    fun search(key: String, index: Int) {
        if (uSearch == null) {
            uSearch = DefaultUseCase(SearchRepository())
        }
        uSearch?.execute(SearchObserver(searchView!!), SearchTask(key, index))
    }

    private class HotWordObserver(val view: SearchView) : BaseObserver<HotWordResponse>() {

        override fun onNext(t: HotWordResponse) {
            super.onNext(t)
            view.setHotWord(t.list)
        }
    }

    private class SearchObserver(val view: SearchView) : BaseObserver<SearchResponse>() {

        override fun onNext(t: SearchResponse) {
            super.onNext(t)
            view.setResultList(t.entity.list)
        }

        override fun onError(e: Throwable?) {
            super.onError(e)
            view.searchFailed()
        }
    }
}