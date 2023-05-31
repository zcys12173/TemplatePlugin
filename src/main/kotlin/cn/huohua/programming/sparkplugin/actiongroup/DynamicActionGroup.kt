package cn.huohua.programming.sparkplugin.actiongroup

import cn.huohua.programming.sparkplugin.action.ChildAction
import cn.huohua.programming.sparkplugin.action.MixtureAction
import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class DynamicActionGroup : ActionGroup() {
    override fun getChildren(e: AnActionEvent?): Array<AnAction> {
        return arrayOf(ChildAction("ChildAction1"),
                ChildAction("ChildAction2"),
                MixtureAction("ChildMixtureAction"))
    }
}