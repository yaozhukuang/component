package com.zw.yzk.component.common.base

import com.zw.yzk.component.adapter.AbstractRecyclerViewAdapter
import com.zw.yzk.component.adapter.ViewHolder

/**
 * 所有RecyclerView adapter基类
 */
abstract class BaseAdapter<T, H: ViewHolder>(layoutId: Int): AbstractRecyclerViewAdapter<T, H>(layoutId)
