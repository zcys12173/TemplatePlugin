package cn.huohua.programming.sparkplugin.listener

import com.intellij.ide.AppLifecycleListener

class SpApplicationListener: AppLifecycleListener {
    override fun welcomeScreenDisplayed() {
        super.welcomeScreenDisplayed()
        println("welcomeScreenDisplayed")
    }

    override fun appWillBeClosed(isRestart: Boolean) {
        super.appWillBeClosed(isRestart)
        println("appWillBeClosed")
    }
}