object Versions {

    val kotlin_stdlib = "1.3.72"
    val core_ktx = "1.3.2"
    val appcompat = "1.2.0"
    val constraintlayout = "2.0.2"
    val junit = "4.12"
    val androidx_junit = "1.1.2"
    val espresso_core = "3.3.0"
    var material = "1.0.0"

    val support_lib = "27.0.2"
    val retrofit = "2.3.0"
    val glide = "4.10.0"
    val gson = "2.7"
    val eventbus = "3.1.1"
    val soulPermission = "1.2.2_x"
    val niceDialog = "1.2.0"
    val colorTrackTab = "1.0.3"
    var room =  "2.2.5"

}

object Libs {
    //系统库
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_stdlib}"
    val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val junit = "junit:junit:${Versions.junit}"
    val androidx_junit = "androidx.test.ext:junit:${Versions.androidx_junit}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
    val material = "com.google.android.material:material:${Versions.material}"


    //三方库
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val eventbus = "org.greenrobot:eventbus:${Versions.eventbus}"
    val soulPermission = "com.qw:soulpermission:${Versions.soulPermission}"
    val niceDialog =  "com.github.SheHuan:NiceDialog:${Versions.niceDialog}"
    val colorTrackTab = "com.github.hanlonglin:ColorTrackTabLayout:${Versions.colorTrackTab}"

    var room =  "androidx.room:room-runtime:${Versions.room}"
    var room_compiler =  "androidx.room:room-compiler:${Versions.room}"

}