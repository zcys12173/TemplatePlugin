package cn.huohua.programming.sparkplugin.setting

import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JPanel

class SettingComponent {
    private val userNameText = JBTextField()
    private val ageText = JBTextField()
    private val sexText = JBTextField()
    private val ideUserStatus = JBCheckBox("Do you use IntelliJ IDEA? ",false)
    private val mPanel =
        FormBuilder.createFormBuilder()
                .addLabeledComponent(JBLabel("Enter user name"),userNameText,1,false)
                .addLabeledComponent(JBLabel("Enter user age"),ageText,1,false)
                .addLabeledComponent(JBLabel("Enter user sex"),sexText,1,false)
                .addComponent(ideUserStatus,1)
                .addComponentFillVertically(JPanel(),0)
                .panel


    fun getComponent(): JPanel = mPanel

    fun setUserName(text:String){
        userNameText.text = text
    }

    fun getUsername():String{
        return userNameText.text
    }

    fun setUserState(value:Boolean){
        ideUserStatus.isSelected = value
    }

    fun getUserStatus():Boolean{
        return ideUserStatus.isSelected
    }

    fun setAge(age:Int){
        ageText.text = age.toString()
    }

    fun getAge():Int{
        return ageText.text.toInt()
    }

    fun setSex(sex:Int){
        sexText.text = if(sex == 0){"女"}else{"男"}
    }

    fun getSex():Int{
        return if(sexText.text == "男"){1}else{0}
    }
}