package cn.huohua.programming.sparkplugin.persistence

import com.google.gson.Gson
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.Converter
import com.intellij.util.xmlb.XmlSerializerUtil
import com.intellij.util.xmlb.annotations.OptionTag

/**
 * 注：需要在xml中注册
 * https://plugins.jetbrains.com/docs/intellij/persisting-state-of-components.html#implementing-the-state-class
 */
@State(
        name = "org.intellij.sdk.settings.AppSettingsState",
        storages = [Storage("SdkSettingsPlugin.xml")]
)
class SpSettingState: PersistentStateComponent<SpSettingState> {
    var userName:String = "No UserName"
    var isUseIdea:Boolean = false

    /**
     * 非基本数据类型需要使用Convert处理
     */
    @OptionTag(converter = CustomDataConvert::class)
    var customData:CustomData = CustomData(1,1)


    override fun getState(): SpSettingState {
        println("SpSettingState->getState")
        return this
    }

    override fun loadState(state: SpSettingState) {
        println("SpSettingState->loadState")
        XmlSerializerUtil.copyBean(state,this)
    }
}

data class CustomData(var age:Int,var sex:Int)


class CustomDataConvert: Converter<CustomData>() {
    override fun toString(value: CustomData): String? {
        return Gson().toJson(value)
    }

    override fun fromString(value: String): CustomData? {
        return Gson().fromJson<CustomData>(value,CustomData::class.java)
    }

}