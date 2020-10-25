/**
 *
 * create by DragonForest at 2020/10/25
 */
object AndroidConfig {
    val compileSdkVersion = 29
    val buildToolsVersion = "29.0.3"
    val defaultConfig = DefaultConfig()

    class DefaultConfig {
        val applicationId = "com.dragonforest.easybook"
        val minSdkVersion = 16
        val targetSdkVersion = 29
        val versionCode = 1
        val versionName = "1.0"
    }
}