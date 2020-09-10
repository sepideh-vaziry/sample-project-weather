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

    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitInterceptor)
    implementation(Libraries.daggerHilt)
    kapt(Libraries.daggerHiltCompiler)
    implementation(Libraries.gson)
    implementation(Libraries.gsonConverter)
    implementation(Libraries.kotlin)
    implementation(Libraries.timber)
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutines)

    //Test
    testImplementation(TestLibraries.junit)
    testImplementation(TestLibraries.mockito)
    testImplementation(TestLibraries.assertj)
    testImplementation(TestLibraries.mockitoInline)
}
