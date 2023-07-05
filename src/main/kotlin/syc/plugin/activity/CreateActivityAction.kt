package syc.plugin.activity

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.DialogWrapper
import syc.plugin.createFile
import syc.plugin.getRootPath
import syc.plugin.isAndroidModulePath
import syc.plugin.packageToPath
import javax.swing.Icon
import javax.swing.ImageIcon

class CreateActivityAction(icon:Icon):AnAction("Create Activity","创建新的Activity",icon) {

    override fun update(e: AnActionEvent) {
        super.update(e)
        val path =  e.getData(PlatformDataKeys.VIRTUAL_FILE)?.path?:""
        val visible = path.isAndroidModulePath()
        e.presentation.isEnabledAndVisible = visible
    }
    override fun actionPerformed(e: AnActionEvent) {
        val path =  e.getData(PlatformDataKeys.VIRTUAL_FILE)?.path?:""
        val dialog = CreateActivityDialog(calculatePackageName(path))
        dialog.show()
        if (dialog.exitCode == DialogWrapper.OK_EXIT_CODE) {
            val layoutName = dialog.layoutName
            val activityName = dialog.activityName
            val viewModelName = dialog.viewModelName
            val packageName = dialog.packageName
            val path = e.getData(PlatformDataKeys.VIRTUAL_FILE)?.path?:""
            createViewModelFile(viewModelName,path,packageName)
            createLayoutFile(layoutName,path,packageName,viewModelName)
            createActivityFile(activityName,path,packageName,viewModelName,layoutName)
            e.project?.baseDir?.refresh(false,true)
        }
    }


    private fun createViewModelFile(name:String,path:String,packageName:String){
        val file = createFile("$path/vm/${name}.kt")
        file.writeText("""
            package $packageName.vm

            import com.syc.mvvm.framework.base.BaseViewModel

            class $name : BaseViewModel() {

            }
        """.trimIndent())
    }
    private fun createLayoutFile(name:String,path:String,packageName:String,vmName:String,){
        val file = createFile("${getSourceSetDir(path)}/res/layout/${name}.xml")
        file.writeText("""
            <?xml version="1.0" encoding="utf-8"?>
            <layout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:app="http://schemas.android.com/apk/res-auto">
            
                <data>
            
                    <variable
                        name="vm"
                        type="$packageName.vm.$vmName" />
                </data>
            
                <androidx.constraintlayout.widget.ConstraintLayout
                    android:layout_width="match_parent"
                    android:layout_height="match_parent">
            
                </androidx.constraintlayout.widget.ConstraintLayout>
            </layout>
        """.trimIndent())
    }


    private fun createActivityFile(name:String,path:String,packageName:String,vmName:String,layoutName:String){
        val file = createFile("$path/view/activity/${name}.kt")
        val bindingClassName = layoutName.split("_").joinToString("") { it.capitalize() }+"Binding"
        println(bindingClassName)
        file.writeText("""
            package $packageName.view.activity

            import android.os.Bundle
            import androidx.databinding.DataBindingUtil
            import com.syc.mvvm.framework.base.BaseActivity
            import com.syc.mvvm.framework.base.createViewModel
            import $packageName.databinding.$bindingClassName
            import $packageName.vm.$vmName

            class ${name}:BaseActivity() {
                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    val binding = DataBindingUtil.setContentView<$bindingClassName>(this, R.layout.$layoutName)
                    binding.vm = createViewModel(this, $vmName::class.java)
                }
            }
        """.trimIndent())
    }


    private fun getSourceSetDir(path: String):String{
        return if(path.contains("/kotlin/")){
            path.substringBefore("/kotlin/")
        }else{
            path.substringBefore("/java/")
        }
    }

    private fun calculatePackageName(path:String):String{
        return if(path.contains("/kotlin/")){
            path.substringAfter("/kotlin/").replace("/",".")
        }else{
            path.substringAfter("/java/").replace("/",".")
        }
    }
}