package cn.huohua.programming.sparkplugin.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class FileAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        println("FileAction->actionPerformed")
    }
}