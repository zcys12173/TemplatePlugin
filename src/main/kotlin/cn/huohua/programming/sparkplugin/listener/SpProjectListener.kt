package cn.huohua.programming.sparkplugin.listener

import cn.huohua.programming.sparkplugin.service.SpProjectService
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.project.ProjectManagerListener

class SpProjectListener:ProjectManagerListener {
    override fun projectClosed(project: Project) {
        super.projectClosed(project)
        println("projectClosed")//此方法没有走
    }

    override fun projectClosing(project: Project) {
        super.projectClosing(project)
        println("projectClosing")
    }

    override fun projectOpened(project: Project) {
        super.projectOpened(project)
        println("projectOpened:${project.basePath}")
        project.getService(SpProjectService::class.java).initFileListener()
    }
}