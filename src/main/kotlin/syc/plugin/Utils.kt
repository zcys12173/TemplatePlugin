package syc.plugin

import java.io.File

fun createFile(path: String): File {
    val file = File(path)
    file.parentFile.takeIf { !it.exists() }?.run {
        mkdirs()
    }
    file.createNewFile()
    return file
}

fun createDir(path: String):File{
    return File(path).apply {
        if(!exists()){
            mkdirs()
        }
    }
}

fun String.getRootPath():String{
    if(isAndroidModulePath()){
        return substringBefore("src",this)
    }else{
        throw Exception("不是一个Android module 目录")
    }
}

fun String.isAndroidModulePath():Boolean{
    val moduleRootPath = substringBefore("src",this)
    return File(moduleRootPath,"build.gradle").exists()//通过gradle文件是否存在俩判断是否是module工程
}

fun String.packageToPath() = this.replace(".","/")


