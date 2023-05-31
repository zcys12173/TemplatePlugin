package syc.plugin.module.processor

import com.intellij.openapi.project.Project
import com.intellij.util.ReflectionUtil
import org.apache.commons.io.FileUtils
import syc.plugin.TemplatePlugin
import syc.plugin.createDir
import syc.plugin.createFile
import java.io.File

class CreateModuleProcessor(
    private val project: Project,
    private val moduleName: String,
    private val packageName: String
) {
    private val packagePath = packageName.replace(".","/")
    fun process(){
        modifySettingFile()
        createBuildFile()
        createProguard()
        createAndroidTest()
        createTest()
        createMainSource()
        createSampleSource()
        project.baseDir.refresh(false,true)
    }

    private fun createSampleSource() {
        createJavaSource("sample")
        createRes("sample")
        createThemes()
        createMipmap()
        createSampleManifest()
    }

    private fun createSampleManifest() {
        val manifestFile = createFile("${getModuleFile()}/src/sample/AndroidManifest.xml")
        manifestFile.writeText("""
            <?xml version="1.0" encoding="utf-8"?>
            <manifest xmlns:android="http://schemas.android.com/apk/res/android"
                package="$packageName">

                <application
                    android:name="com.syc.mvvm.framework.base.FrameworkApplication"
                    android:allowBackup="true"
                    android:icon="@mipmap/ic_launcher"
                    android:label="Sample"
                    android:supportsRtl="true"
                    android:theme="@style/Theme.SycApp">
                 
                </application>

            </manifest>
        """.trimIndent())
    }

    private fun createMipmap() {
        val destFile = createFile("${getModuleFile()}/src/sample/res/mipmap/ic_launcher.webp")
        val url = TemplatePlugin::class.java.getResource("/image/ic_launcher.webp")
        FileUtils.copyURLToFile(url,destFile)
    }

    private fun createThemes() {
        val file = createFile("${getModuleFile()}/src/sample/res/values/themes.xml")
        file.writeText("""
            <resources xmlns:tools="http://schemas.android.com/tools">
                <!-- Base application theme. -->
                <style name="Base.Theme.SycApp" parent="Theme.Material3.DayNight.NoActionBar">
                    <!-- Customize your light theme here. -->
                    <!-- <item name="colorPrimary">@color/my_light_primary</item> -->
                </style>

                <style name="Theme.SycApp" parent="Base.Theme.SycApp" />
            </resources>
        """.trimIndent())
    }

    private fun createMainSource() {
        createJavaSource("main")
        createRes("main")
        createManifest("main")
    }

    private fun modifySettingFile() {
        val settingFile = File("${project.basePath}/settings.gradle")
        settingFile.appendText("""
            
            include ':$moduleName'
        """.trimIndent())
    }

    private fun createAndroidTest() {
        val file = createFile("${getModuleFile()}/src/androidTest/java/$packagePath/ExampleInstrumentedTest.kt")
        file.writeText("""
            package $packageName

            import androidx.test.platform.app.InstrumentationRegistry
            import androidx.test.ext.junit.runners.AndroidJUnit4

            import org.junit.Test
            import org.junit.runner.RunWith

            import org.junit.Assert.*

            /**
             * Instrumented test, which will execute on an Android device.
             *
             * See [testing documentation](http://d.android.com/tools/testing).
             */
            @RunWith(AndroidJUnit4::class)
            class ExampleInstrumentedTest {
                @Test
                fun useAppContext() {
                    // Context of the app under test.
                    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
                    assertEquals("com.syc.mvvm.user_module", appContext.packageName)
                }
            }
        """.trimIndent())
    }

    private fun createTest(){
        val file = createFile("${getModuleFile()}/src/test/java/$packagePath/ExampleUnitTest.kt")
        file.writeText("""
            package $packageName

            import org.junit.Test

            import org.junit.Assert.*

            /**
             * Example local unit test, which will execute on the development machine (host).
             *
             * See [testing documentation](http://d.android.com/tools/testing).
             */
            class ExampleUnitTest {
                @Test
                fun addition_isCorrect() {
                    assertEquals(4, 2 + 2)
                }
            }
        """.trimIndent())
    }





    private fun createManifest(sourceName: String) {
        val manifestFile = createFile("${getModuleFile()}/src/$sourceName/AndroidManifest.xml")
        manifestFile.writeText("""
            <?xml version="1.0" encoding="utf-8"?>
            <manifest xmlns:android="http://schemas.android.com/apk/res/android">

            </manifest>
        """.trimIndent())
    }

    private fun createRes(sourceName: String) {
        createDir("${getModuleFile()}/src/$sourceName/res/layout")
        createDir("${getModuleFile()}/src/$sourceName/res/drawable")
        createDir("${getModuleFile()}/src/$sourceName/res/values")
    }

    private fun createJavaSource(sourceName:String) {
        createDir("${getModuleFile()}/src/$sourceName/java/$packagePath")
    }


    private fun createProguard() {

        val buildFile = File(getModuleFile(),"proguard-rules.pro")
        buildFile.writeText("""
            # Add project specific ProGuard rules here.
            # You can control the set of applied configuration files using the
            # proguardFiles setting in build.gradle.
            #
            # For more details, see
            #   http://developer.android.com/guide/developing/tools/proguard.html

            # If your project uses WebView with JS, uncomment the following
            # and specify the fully qualified class name to the JavaScript interface
            # class:
            #-keepclassmembers class fqcn.of.javascript.interface.for.webview {
            #   public *;
            #}

            # Uncomment this to preserve the line number information for
            # debugging stack traces.
            #-keepattributes SourceFile,LineNumberTable

            # If you keep the line number information, uncomment this to
            # hide the original source file name.
            #-renamesourcefileattribute SourceFile
        """.trimIndent())
    }

    private fun getModuleFile():File{
        val moduleFile = File("${project.basePath}/$moduleName")
        if(!moduleFile.exists()){
           moduleFile.mkdirs()
        }
        println("---${moduleFile.path}")
        return moduleFile
    }

    private fun createBuildFile() {
        val buildFile = File(getModuleFile(),"build.gradle")
        buildFile.createNewFile()
        buildFile.writeText("""
            plugins {
            //    id 'application-plugin'
                id 'library-plugin'
            }
            android {
                namespace '$packageName'
            }

        """.trimIndent())
    }
}