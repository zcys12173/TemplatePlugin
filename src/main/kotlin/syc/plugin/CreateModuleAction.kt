package syc.plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.ui.UiInterceptors
import com.intellij.ui.components.DialogManager
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import syc.plugin.dialog.CreateModuleComponent
import syc.plugin.dialog.CreateModuleDialog
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel

class CreateModuleAction:AnAction("Create Module") {
    override fun actionPerformed(e: AnActionEvent) {
        JBPopupFactory.getInstance().
        createComponentPopupBuilder(component,null)
            .createPopup()
            .showInFocusCenter()
//        CreateModuleDialog().apply {
//            showAndGet()
//        }
    }

    private val moduleName = JBTextField()
    private val packageName = JBTextField()
    private val  component :JComponent = JPanel(BorderLayout()).apply {
        //添加一个标题，然后下面还有两个输入框
        preferredSize = Dimension(400,400)
        add(JLabel("Create Module"),BorderLayout.NORTH)
        val mPanl = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("module name"),moduleName,1,false)
            .addLabeledComponent(JBLabel("package name"),packageName,1,false)
            .addComponent(JButton("确定"))
            .addComponentFillVertically(JPanel(),0)
            .panel
        add(mPanl,BorderLayout.CENTER)
    }

}