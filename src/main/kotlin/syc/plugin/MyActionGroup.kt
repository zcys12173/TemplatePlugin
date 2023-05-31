package syc.plugin

import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import syc.plugin.module.action.CreateModuleAction

class MyActionGroup: ActionGroup() {
    override fun getChildren(e: AnActionEvent?): Array<AnAction> {
       return arrayOf(
           CreateModuleAction()
       )
    }


}