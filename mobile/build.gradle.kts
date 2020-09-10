plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.daggerHiltPlugin)
}

android {

    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        applicationId = ProjectConfig.applicationId

        minSdkVersion (AndroidSdk.min)
        targetSdkVersion (AndroidSdk.target)
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        multiDexEnabled = true

        testInstrumentationRunner = TestLibraries.testInstrumentationRunner

        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTarget
    }

    packagingOptions {
        exclude("asm-license.txt")
    }

    buildFeatures{
        viewBinding = true
    }

}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    implementation(Libraries.appCompat)
    implementation(Libraries.supportV4)
    implementation(Libraries.material)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.fragmentKtx)
    implementation(Libraries.lifecycle)
    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUi)
    kapt(Libraries.lifecycleCompiler)
    implementation(Libraries.daggerHilt)
    kapt(Libraries.daggerHiltCompiler)
    implementation(Libraries.hiltViewModel)
    kapt(Libraries.hiltViewModelCompiler)
    implementation(Libraries.kotlin)
    implementation(Libraries.retrofit)
    implementation(Libraries.gsonConverter)
    implementation(Libraries.timber)

}
