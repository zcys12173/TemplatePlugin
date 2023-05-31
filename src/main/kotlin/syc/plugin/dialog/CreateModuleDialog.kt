package syc.plugin.dialog

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import java.awt.Dimension
import java.awt.Font
import java.awt.Font.DIALOG
import javax.swing.Action
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel


class CreateModuleDialog : DialogWrapper(true) {

    init {
        isModal = true
        title = "Create Module Dialog"
    }


    override fun createCenterPanel(): JComponent? {
        return JLabel("为什么不显示呢")
//        val dialogPanel = JPanel(BorderLayout())
//        dialogPanel.size = Dimension(100, 100)
//        val label = JLabel("testing")
//        label.preferredSize = Dimension(100, 100)
//        dialogPanel.add(label, BorderLayout.CENTER)
//
//        return dialogPanel
    }


//    override fun getPreferredFocusedComponent(): JComponent {
//        return CreateModuleComponent().getComponent()
//    }


}

class CreateModuleComponent {
    private val moduleName = JBTextField()
    private val packageName = JBTextField()
    private val mPanel =
        FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("module name"),moduleName,1,false)
            .addLabeledComponent(JBLabel("package name"),packageName,1,false)
            .addComponent(JButton("确定"))
            .addComponentFillVertically(JPanel(),0)
            .panel


    fun getComponent(): JPanel = mPanel.apply {
        preferredSize = Dimension(400,400)
    }

    fun setUserName(text:String){
        moduleName.text = text
    }

    fun getUsername():String{
        return moduleName.text
    }

    fun setAge(age:Int){
        packageName.text = age.toString()
    }

    fun getAge():Int{
        return packageName.text.toInt()
    }

}