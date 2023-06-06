package syc.plugin.module.dialog

import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.ui.DialogWrapper
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.event.DocumentEvent


class CreateModuleDialog : DialogWrapper(true) {
    private val defaultPackageName = "com.syc.mvvm."
    private lateinit var packageNameField: JTextField
    private lateinit var moduleNameField: JTextField

    init {
        title = "Create Module"
        init()
    }

    override fun createCenterPanel(): JComponent {
        val panel = JPanel(GridBagLayout())
        val constraints = GridBagConstraints()
        constraints.fill = GridBagConstraints.HORIZONTAL
        constraints.insets = Insets(5, 5, 5, 5)

        // ModuleName Label and TextField
        val moduleNameLabel = JLabel("Module name:")
        moduleNameField = JTextField()
        moduleNameField.preferredSize = Dimension(300, moduleNameField.preferredSize?.height ?: 0)
        constraints.gridx = 0
        constraints.gridy = 0
        panel.add(moduleNameLabel, constraints)
        constraints.gridx = 1
        constraints.gridy = 0
        panel.add(moduleNameField, constraints)

        // PackageName Label and TextField
        val packageNameLabel = JLabel("Package name:")
        packageNameField = JTextField()
        packageNameField.text = defaultPackageName
        packageNameField.preferredSize = Dimension(300, packageNameField.preferredSize?.height ?: 0)
        constraints.gridx = 0
        constraints.gridy = 1
        panel.add(packageNameLabel, constraints)
        constraints.gridx = 1
        constraints.gridy = 1
        panel.add(packageNameField, constraints)


        moduleNameField.document.addDocumentListener(object : DocumentListener, javax.swing.event.DocumentListener {
            override fun insertUpdate(e: DocumentEvent?) {
                packageNameField.text = getPackageName(moduleNameField.text)
            }

            override fun removeUpdate(e: DocumentEvent?) {
                packageNameField.text = getPackageName(moduleNameField.text)
            }

            override fun changedUpdate(e: DocumentEvent?) {

            }

        })
        return panel
    }

    private fun getPackageName(moduleName:String):String{
        val splits = moduleName.split("-")
        val name = if(splits.size > 1){
            splits[1]
        }else{
            splits[0]
        }
        return defaultPackageName+name
    }

    val packageName: String
        get() = packageNameField.text.orEmpty()

    val moduleName: String
        get() = moduleNameField.text.orEmpty()
}