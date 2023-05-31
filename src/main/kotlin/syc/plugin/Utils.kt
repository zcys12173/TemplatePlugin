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