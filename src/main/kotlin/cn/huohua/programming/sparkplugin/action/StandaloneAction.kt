package cn.huohua.programming.sparkplugin.action

import cn.huohua.programming.sparkplugin.service.SpApplicationService
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager

/**
 * 独立的Action
 */
class StandaloneAction:AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        println("on StandaloneAction action run")
        val applicationService = ApplicationManager.getApplication().getService(SpApplicationService::class.java)
        applicationService.log()
    }
}