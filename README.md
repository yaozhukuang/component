# component
利用注解实现组件化

整个App分为module_article,module_account,module_project,module_search和App几个可以单独运行的模块和一些公共的组件包括banner，network
sdk，res，adapter等。组件由common统一进行依赖，所有的模块依赖common。在开发过程中将工程目录下的build.gradle中的canRunAlone设置为true，
五个模块（module开头和App）均可以独立运行，当canRunAlone设置为false除App以为的其它模块将作为Applibrary依赖

图片如下
![avatar](https://github.com/yaozhukuang/component/blob/master/caputrue/component.gif)
