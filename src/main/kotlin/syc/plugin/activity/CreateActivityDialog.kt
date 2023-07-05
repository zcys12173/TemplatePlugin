package syc.plugin.activity

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
import com.intellij.openapi.editor.event.DocumentListener

class CreateActivityDialog(private val defaultPackageName: String) : DialogWrapper(true) {
    private  val activityNameField: JTextField = JTextField()
    private  val layoutNameField: JTextField = JTextField()
    private  val viewModelNameField: JTextField = JTextField()
    private  val packageNameField: JTextField = JTextField().apply {
        text = defaultPackageName
    }

    init {
        title = "Create Module"
        init()
    }


    private fun addActivityNameComponent(panel:JPanel,constraints: GridBagConstraints){
        // ModuleName Label and TextField
        val activityNameLabel = JLabel("Activity Name:")
        activityNameField.preferredSize = Dimension(300, activityNameField.preferredSize?.height ?: 0)
        constraints.gridx = 0
        constraints.gridy = 0
        panel.add(activityNameLabel, constraints)
        constraints.gridx = 1
        constraints.gridy = 0
        panel.add(activityNameField, constraints)
    }

    private fun addLayoutNameComponent(panel:JPanel,constraints: GridBagConstraints){
        // ModuleName Label and TextField
        val layoutNameLabel = JLabel("Layout Name:")
        layoutNameField.preferredSize = Dimension(300, layoutNameField.preferredSize?.height ?: 0)
        constraints.gridx = 0
        constraints.gridy = 1
        panel.add(layoutNameLabel, constraints)
        constraints.gridx = 1
        constraints.gridy = 1
        panel.add(layoutNameField, constraints)
    }

    private fun addViewModelNameComponent(panel:JPanel,constraints: GridBagConstraints){
        // ModuleName Label and TextField
        val vmNameLabel = JLabel("ViewModel Name:")
        viewModelNameField.preferredSize = Dimension(300, viewModelNameField.preferredSize?.height ?: 0)
        constraints.gridx = 0
        constraints.gridy = 2
        panel.add(vmNameLabel, constraints)
        constraints.gridx = 1
        constraints.gridy = 2
        panel.add(viewModelNameField, constraints)
    }

    private fun addPackageNameComponent(panel:JPanel,constraints: GridBagConstraints){
        // ModuleName Label and TextField
        val packageNameLabel = JLabel("Package Name:")
        packageNameField.preferredSize = Dimension(300, packageNameField.preferredSize?.height ?: 0)
        constraints.gridx = 0
        constraints.gridy = 3
        panel.add(packageNameLabel, constraints)
        constraints.gridx = 1
        constraints.gridy = 3
        panel.add(packageNameField, constraints)
    }
    override fun createCenterPanel(): JComponent {
        val panel = JPanel(GridBagLayout())
        val constraints = GridBagConstraints()
        constraints.fill = GridBagConstraints.HORIZONTAL
        constraints.insets = Insets(5, 5, 5, 5)

       addActivityNameComponent(panel,constraints)

       addLayoutNameComponent(panel,constraints)

        addViewModelNameComponent(panel,constraints)

        addPackageNameComponent(panel,constraints)

        activityNameField.document.addDocumentListener(object : DocumentListener, javax.swing.event.DocumentListener {
            override fun insertUpdate(e: DocumentEvent?) {
                onActivityNameChanged()
            }

            override fun removeUpdate(e: DocumentEvent?) {
                onActivityNameChanged()
            }

            override fun changedUpdate(e: DocumentEvent?) {

            }

        })
        return panel
    }

    private fun onActivityNameChanged(){
        val name = activityNameField.text.substringBefore("Activity")
        viewModelNameField.text = "${name}Vm"
        layoutNameField.text = "activity_${name.toLowerCase()}"
    }

    val packageName: String
        get() = packageNameField.text.orEmpty()

    val activityName: String
        get() = activityNameField.text.orEmpty()


    val layoutName: String
        get() = layoutNameField.text.orEmpty()

    val viewModelName:String
        get() = viewModelNameField.text.orEmpty()
}