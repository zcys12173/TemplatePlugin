package cn.huohua.programming.sparkplugin.setting

import cn.huohua.programming.sparkplugin.persistence.SpSettingState
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

class SpApplicationSetting:Configurable {
    private val component = SettingComponent()
    private val settingState = ApplicationManager.getApplication().getService(SpSettingState::class.java)
    override fun createComponent(): JComponent {
        println("SpApplicationSetting->createComponent")
        return component.getComponent()
    }

    override fun isModified(): Boolean {
        println("SpApplicationSetting->isModified")
        return settingState.isUseIdea != component.getUserStatus()
                || settingState.userName != component.getUsername()
                || settingState.customData.age == component.getAge()
                || settingState.customData.sex == component.getSex()

    }

    override fun apply() {
        println("SpApplicationSetting->apply")
        val newName = component.getUsername()
        val newStatus = component.getUserStatus()
        val newSex = component.getSex()
        val newAge = component.getAge()
        settingState.isUseIdea = newStatus
        settingState.userName = newName
        settingState.customData.sex = newSex
        settingState.customData.age = newAge
    }


    override fun reset() {
        println("SpApplicationSetting->reset")
        component.setUserName(settingState.userName)
        component.setUserState(settingState.isUseIdea)
        component.setAge(settingState.customData.age)
        component.setSex(settingState.customData.sex)
    }
    override fun getDisplayName(): String {
        return "SpApplicationSetting"
    }
}