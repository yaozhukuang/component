package com.zw.yzk.module.account.ui.view

import com.zw.yzk.module.account.data.NavigationGroup


interface NavigationView {
    fun setNavigationList(list: List<NavigationGroup>)
}