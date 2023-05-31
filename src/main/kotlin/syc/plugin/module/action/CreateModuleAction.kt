package syc.plugin.module.action

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.DialogWrapper
import syc.plugin.module.dialog.CreateModuleDialog
import syc.plugin.module.processor.CreateModuleProcessor

class CreateModuleAction : AnAction("Create Module","创建新的Module",AllIcons.Actions.ProjectDirectory) {
    override fun actionPerformed(e: AnActionEvent) {
        val dialog = CreateModuleDialog()
        dialog.show()
        if (dialog.exitCode == DialogWrapper.OK_EXIT_CODE) {
            val packageName = dialog.packageName
            val moduleName = dialog.moduleName
            CreateModuleProcessor(e.project!!,moduleName,packageName).process()
        }
    }


}

