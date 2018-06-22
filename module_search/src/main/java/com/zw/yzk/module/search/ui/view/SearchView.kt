package com.zw.yzk.module.search.ui.view

import com.zw.yzk.module.search.data.HotWord
import com.zw.yzk.module.search.data.Search

interface SearchView {
    fun setResultList(list: List<Search>)

    fun searchFailed()

    fun setHotWord(list: List<HotWord>)
}