repositories {
    jcenter()
}

plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.4.0"
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}