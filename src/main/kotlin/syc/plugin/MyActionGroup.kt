package syc.plugin

import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import syc.plugin.activity.CreateActivityAction
import syc.plugin.module.action.CreateModuleAction
import javax.swing.ImageIcon

class MyActionGroup: ActionGroup() {
    override fun getChildren(e: AnActionEvent?): Array<AnAction> {
        val url = TemplatePlugin::class.java.getResource("/image/icon.png")
       return arrayOf(
           CreateModuleAction(),
           CreateActivityAction(ImageIcon(url))
       )
    }


}