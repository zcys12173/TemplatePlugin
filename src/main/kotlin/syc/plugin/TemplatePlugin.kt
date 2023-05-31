package syc.plugin

import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.project.Project


class TemplatePlugin: ProjectComponent {
    companion object {
        fun getInstance(project: Project): TemplatePlugin {
            return project.getComponent(TemplatePlugin::class.java)
        }
    }
}