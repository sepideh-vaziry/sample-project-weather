const val kotlinVersion = "1.4.0"
const val daggerHiltVersion = "2.28.3-alpha"

//**************************************************************************************************
object ProjectConfig {
    const val applicationId = "com.example.weather"  //Also be changed at AuthorizationInterceptor class
    const val versionCode = 1
    const val versionName = "1.0.0"

    const val jvmTarget = "1.8"
}

//**************************************************************************************************
object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "4.0.1"
        const val updatePluginVersion = "0.29.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"
    const val androidLibrary = "com.android.library"
    const val hiltAndroidPlugin = "com.google.dagger:hilt-android-gradle-plugin:$daggerHiltVersion"
    const val daggerHiltPlugin = "dagger.hilt.android.plugin"

}

//**************************************************************************************************
object AndroidSdk {
    const val min = 17
    const val compile = 30
    const val target = compile
}

//**************************************************************************************************
object Libraries {

    object Versions {
        const val coroutinesVersion = "1.3.9"
        const val appCompatVersion = "1.3.0-alpha02"
        const val supportV4Version = "1.0.0"
        const val androidAnnotationsVersion = "1.2.0-alpha01"
        const val constraintLayoutVersion = "2.0.1"
        const val materialVersion = "1.3.0-alpha02"
        const val fragmentKtxVersion = "1.3.0-alpha08"
        const val lifecycleVersion = "2.3.0-alpha07"
        const val lifecycleCompilerVersion = "2.3.0-alpha07"
        const val navigationVersion = "1.0.0"
        const val hiltViewModelVersion = "1.0.0-alpha02"
        const val retrofitVersion = "2.6.0"          //For support android 4.4
        const val retrofitInterceptorVersion = "3.12.2"    //For support android 4.4
        const val gsonVersion = "2.8.5"
        const val gsonConverterVersion = "2.6.0"
        const val timberVersion = "4.7.1"
        const val picassoVersion = "2.71828"
    }

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val supportV4 = "androidx.legacy:legacy-support-v4:${Versions.supportV4Version}"
    const val supportAnnotations = "androidx.annotation:annotation:${Versions.androidAnnotationsVersion}"


    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"

    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtxVersion}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleCompilerVersion}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    const val navigationFragment = "android.arch.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationUi = "android.arch.navigation:navigation-ui-ktx:${Versions.navigationVersion}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitInterceptorVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverterVersion}"

    const val daggerHilt = "com.google.dagger:hilt-android:$daggerHiltVersion"
    const val daggerHiltCompiler ="com.google.dagger:hilt-android-compiler:$daggerHiltVersion"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModelVersion}"
    const val hiltViewModelCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltViewModelVersion}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
    const val picasso = "com.squareup.picasso:picasso:${Versions.picassoVersion}"

}

//**************************************************************************************************
object TestLibraries {

    object Versions {
        const val jUnitVersion = "4.13"
        const val assertJVersion = "3.16.1"
        const val mockitoVersion = "3.5.7"
        const val robolectricVersion = "3.1.1"
    }

    const val testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"

    const val junit = "junit:junit:${Versions.jUnitVersion}"
    const val assertj = "org.assertj:assertj-core:${Versions.assertJVersion}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockitoVersion}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoVersion}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectricVersion}"

}