package cn.huohua.programming.sparkplugin.actiongroup

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup

/**
 * 自定义DefaultActionGroup，在xml中配置子Actions
 */
class CustomDefaultActionGroup:DefaultActionGroup() {
    override fun update(e: AnActionEvent) {
        super.update(e)
//        Enable/disable depending on whether a user is editing...
    }
}