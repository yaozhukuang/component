package com.zw.yzk.component.common

import com.zw.yzk.component.annotation.XModuleInject
import com.zw.yzk.component.annotation.XModule
import com.zw.yzk.component.common.constant.*
import com.zw.yzk.component.common.module.*

/**
 * @author zhanwei
 */
@XModuleInject
class ModuleImpl {

    @XModule(MODULE_ACCOUNT)
    var accountModule: AccountModule? = null
    @XModule(MODULE_ARTICLE)
    var articleModule: ArticleModule? = null
    @XModule(MODULE_PROJECT)
    var projectModule: ProjectModule? = null
    @XModule(MODULE_SEARCH)
    var searchModule: SearchModule? = null
    @XModule(MODULE_APP)
    var appModule: AppModule? = null
}
