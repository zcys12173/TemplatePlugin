package cn.huohua.programming.sparkplugin.action

import cn.huohua.programming.sparkplugin.service.SpProjectService
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * 仅用于ActionGroup中的子Action
 */
class ChildAction(private val text:String = "ChildAction"):AnAction(text) {
    override fun actionPerformed(e: AnActionEvent) {
        println("$text run")
        val projectService = e.project?.getService(SpProjectService::class.java)
        projectService?.log()
    }
}