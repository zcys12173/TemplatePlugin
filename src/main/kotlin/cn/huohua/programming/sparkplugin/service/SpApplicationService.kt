package cn.huohua.programming.sparkplugin.service


/**
 * 获取方式：val applicationService = ApplicationManager.getApplication().getService(SpApplicationService::class.java)
 * 也可通过单例方式来获取
 */
class SpApplicationService {
    init {
        println("application service init")
    }

    fun log(){
        println("application service run log")
    }
}