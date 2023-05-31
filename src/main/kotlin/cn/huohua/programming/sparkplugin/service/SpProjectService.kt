package cn.huohua.programming.sparkplugin.service

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFileEvent
import com.intellij.openapi.vfs.VirtualFileListener
import java.io.File

/**
 * 获取方式 projectService = e.project?.getService(SpProjectService::class.java)
 * 也可以通过单例来处理
 */
@Service
class SpProjectService(private val project:Project) {
    init {
        println("SpProjectService init")
    }

    fun log(){
        println("SpProjectService run log")
    }

    fun initFileListener(){
        println("SpProjectService->initFileListener")
//        val vf = LocalFileSystem.getInstance().findFileByIoFile(File(project.basePath+File.pathSeparator+"resources/test1/"))
        LocalFileSystem.getInstance().addVirtualFileListener(object : VirtualFileListener {
            override fun fileDeleted(event: VirtualFileEvent) {
                super.fileDeleted(event)
                println("The file ${event.file.path} has been delete")
            }

            override fun fileCreated(event: VirtualFileEvent) {
                super.fileCreated(event)
                println("The file ${event.file.path} has been created")
            }
        })
    }
}