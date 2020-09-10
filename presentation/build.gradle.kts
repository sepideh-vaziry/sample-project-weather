plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.daggerHiltPlugin)
}

android {

    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        minSdkVersion (AndroidSdk.min)
        targetSdkVersion (AndroidSdk.target)
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = TestLibraries.testInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTarget
    }

}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Libraries.lifecycle)
    kapt(Libraries.lifecycleCompiler)
    implementation(Libraries.viewModel)
    implementation(Libraries.daggerHilt)
    kapt(Libraries.daggerHiltCompiler)
    implementation(Libraries.hiltViewModel)
    kapt(Libraries.hiltViewModelCompiler)
    implementation(Libraries.kotlin)
    implementation(Libraries.timber)
    implementation(Libraries.coroutines)
    implementation(Libraries.coroutinesCore)

}
