package cn.huohua.programming.sparkplugin.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * 同时展示在ActionGroup中，也单独展示到工具栏中（需要在xml配置group）
 */
class MixtureAction(private val text:String = "MixtureAction"):AnAction(text) {
    override fun actionPerformed(e: AnActionEvent) {
        println("$text run")
    }
}